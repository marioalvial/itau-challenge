package itau.challenge.utils

import itau.challenge.utils.containers.ApplicationContainer
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ExtensionContext.Store.CloseableResource

class IntegrationTestExtension : BeforeAllCallback, CloseableResource {

    override fun beforeAll(context: ExtensionContext?) {
        ApplicationContainer.start()
    }

    override fun close() {
        ApplicationContainer.stop()
    }
}
