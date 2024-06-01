package com.microservices.graphql.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GraphQlExceptionTranslator extends DataFetcherExceptionResolverAdapter {

    Logger log = LogManager.getLogger(GraphQlExceptionTranslator.class);

    @Override
    protected GraphQLError  resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        if(ex instanceof BaseException){
            return getGraphQLErrors((BaseException)ex,env);
        }
        //ERROR CODE CLASS
        return mapToGraphQLError(new Error("UNKNOWN_ERROR",null,"INTERNAL_SERVER_ERROR"),env);
    }

    private GraphQLError getGraphQLErrors (BaseException ex, DataFetchingEnvironment env) {
        if(ex == null){
            return null;
        }
        return mapToGraphQLError(new Error("404",null,"BAD REQUEST"),env);
    }

    private GraphQLError mapToGraphQLError (Error error,DataFetchingEnvironment env) {
        Map<String ,Object> extension = new HashMap<>();
        //Put them In Constants
        extension.put("code",error.getCode());
        extension.put("field",error.getField());
        extension.put("message",error.getMessage());

        return  GraphqlErrorBuilder.newError()
                .message(error.getMessage())
                .errorType(ErrorType.DataFetchingException)
                .location(env.getField().getSourceLocation())
                .path(List.of(env.getField().getName()))
                .extensions(extension)
                .build();
    }
}
