package com.mpc.vauth.controller;

import com.mpc.vauth.config.JwtTokenUtil;
import com.mpc.vauth.model.JwtRequest;
import com.mpc.vauth.model.JwtResponse;
import com.mpc.vauth.model.TransactionResponse;
import com.mpc.vauth.service.JwtUserDetailsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    Logger log = LogManager.getLogger(getClass());

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        log.info("Authentication");
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setJwttoken(token);
        jwtResponse.setUsername(authenticationRequest.getUsername());

        return ResponseEntity.ok(jwtResponse);
    }

    @RequestMapping(value = "check-token", method = RequestMethod.POST)
    public TransactionResponse checkToken(@RequestBody Map<String, Object> request, HttpServletResponse response) throws Exception{
        log.info("Token: "+request.get("token").toString());
        return checkRequestToken(request.get("token").toString());
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            log.info("USER_DISABLED");
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            log.info("INVALID_CREDENTIALS");
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    private TransactionResponse checkRequestToken(String token) throws Exception{
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            String username = jwtTokenUtil.getUsernameFromToken(token);
            log.info("Username: "+username);
            transactionResponse.setStatus(200);
            transactionResponse.setError("");
            transactionResponse.setMessage("Valid");
            final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            log.info("Valid token: "+ jwtTokenUtil.validateToken(token,userDetails));
            log.info("Expired Token: "+jwtTokenUtil.getExpirationDateFromToken(token));
        } catch (Exception e){
            log.info("Token invalid");
            transactionResponse.setStatus(401);
            transactionResponse.setError("Unauthorized");
            transactionResponse.setMessage("Unauthorized");
        }

        return transactionResponse;
    }
}
