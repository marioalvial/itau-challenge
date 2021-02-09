package itau.challenge.password.application

import itau.challenge.password.core.PasswordService
import org.koin.dsl.module

val partnerModule = module {
    single { PasswordService() }
    single { PasswordController(get(), get(), get()) }
}
