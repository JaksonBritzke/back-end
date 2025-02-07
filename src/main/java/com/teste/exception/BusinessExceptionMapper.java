package com.teste.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.core.MediaType;

@Provider
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException> {
   @Override
   public Response toResponse(BusinessException e) {
       return Response.status(Response.Status.BAD_REQUEST)
               .type(MediaType.APPLICATION_JSON)
               .entity(new ErrorResponse(e.getMessage()))
               .build();
   }
}