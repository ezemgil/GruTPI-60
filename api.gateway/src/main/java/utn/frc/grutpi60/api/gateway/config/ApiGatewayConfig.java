package utn.frc.grutpi60.api.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtGrantedAuthoritiesConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;



@Configuration
@EnableWebFluxSecurity
public class ApiGatewayConfig {

    private String rolAdministrador = "ADMINISTRADOR";
    private String rolCliente = "CLIENTE";
    private String apiAlquileres = "/alquileres/**";
    private String apiEstaciones = "/estaciones/**";


    // Gateway
    @Bean
    public RouteLocator configurarRutas(RouteLocatorBuilder builder,
                                        @Value("${tpi-gw-bicis.url-msvc-alquiler}") String uriAlquiler,
                                        @Value("${tpi-gw-bicis.url-msvc-estaciones}") String uriEstaciones) {

        return builder.routes()
                .route(r -> r.path(apiAlquileres).uri(uriAlquiler))
                .route(r -> r.path(apiEstaciones).uri(uriEstaciones))

                .build();

    }

    // Security
    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {


        http.authorizeExchange(e -> e
                        // Los administradores pueden ingresar nuevas estaciones...
                        .pathMatchers(HttpMethod.POST, apiEstaciones)
                        .hasRole(rolAdministrador)


                        // los clientes pueden realizar consultas a las estaciones...
                        .pathMatchers(HttpMethod.GET, apiEstaciones)
                        .hasRole(rolCliente)


                        // los clientes pueden registrar alquileres
                        .pathMatchers(HttpMethod.POST, apiAlquileres)
                        .hasRole(rolCliente)


                        // los clientes pueden registrar la devolucion
                        .pathMatchers(HttpMethod.PATCH, apiAlquileres)
                        .hasRole(rolCliente)


                        // cualquier otra peticion
                        .anyExchange().authenticated()

                ).oauth2ResourceServer(auth -> auth.jwt(Customizer.withDefaults()))
                .csrf(c -> c.disable());

        return http.build();

    }


    @Bean
    public ReactiveJwtAuthenticationConverter jwtAuthenticationConverter() {
        var jwtAuthenticationConverter = new ReactiveJwtAuthenticationConverter();
        var grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

        grantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");

        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");

        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(
                new ReactiveJwtGrantedAuthoritiesConverterAdapter(grantedAuthoritiesConverter));

        // jwtAuthenticationConverter.setPrincipalClaimName("user_name");

        return jwtAuthenticationConverter;
    }
}
