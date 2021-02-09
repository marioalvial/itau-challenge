package itau.challenge.config

import itau.challenge.config.providers.ObjectMapperProvider
import org.koin.dsl.module
import javax.validation.Validation

val configModule = module {
    single { ObjectMapperProvider.provide() }
    single { Validation.buildDefaultValidatorFactory().validator }
}
