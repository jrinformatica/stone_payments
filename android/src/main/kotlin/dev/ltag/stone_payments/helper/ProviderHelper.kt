package dev.ltag.stone_payments.helper

import stone.application.enums.ErrorsEnum
import stone.application.enums.ErrorsEnum.ACCEPTOR_REJECTION
import stone.application.enums.ErrorsEnum.APPNAME_NOT_SET
import stone.application.enums.ErrorsEnum.CANT_READ_CARD_HOLDER_INFORMATION
import stone.application.enums.ErrorsEnum.CANT_READ_CHIP_CARD
import stone.application.enums.ErrorsEnum.CARD_BLOCKED
import stone.application.enums.ErrorsEnum.CARD_GENERIC_ERROR
import stone.application.enums.ErrorsEnum.CARD_READ_CANCELED_ERROR
import stone.application.enums.ErrorsEnum.CARD_READ_ERROR
import stone.application.enums.ErrorsEnum.CARD_READ_MULTI_ERROR
import stone.application.enums.ErrorsEnum.CARD_READ_TIMEOUT_ERROR
import stone.application.enums.ErrorsEnum.CARD_REMOVED_BY_USER
import stone.application.enums.ErrorsEnum.CARD_UNSUPPORTED_ERROR
import stone.application.enums.ErrorsEnum.CONNECTION_NOT_FOUND
import stone.application.enums.ErrorsEnum.CONNECTIVITY_ERROR
import stone.application.enums.ErrorsEnum.COULD_NOT_ACTIVATE_ALL_STONE_CODES
import stone.application.enums.ErrorsEnum.COULD_NOT_ACTIVATE_WITH_ACCEPTOR_CONFIGURATION_UPDATE_DATA_NULL
import stone.application.enums.ErrorsEnum.DATA_CONTAINER_CONSTRAINT_ERROR
import stone.application.enums.ErrorsEnum.DATA_CONTAINER_INTEGRATION_ERROR
import stone.application.enums.ErrorsEnum.DEVICE_NOT_COMPATIBLE
import stone.application.enums.ErrorsEnum.EMAIL_EMPTY
import stone.application.enums.ErrorsEnum.EMAIL_MESSAGE_ERROR
import stone.application.enums.ErrorsEnum.EMAIL_RECIPIENT_EMPTY
import stone.application.enums.ErrorsEnum.EMV_AID_ERROR
import stone.application.enums.ErrorsEnum.EMV_CAPK_ERROR
import stone.application.enums.ErrorsEnum.EMV_FAILED_CARD_CONN_ERROR
import stone.application.enums.ErrorsEnum.EMV_GENERIC_ERROR
import stone.application.enums.ErrorsEnum.EMV_INITIALIZATION_ERROR
import stone.application.enums.ErrorsEnum.EMV_NO_APP_ERROR
import stone.application.enums.ErrorsEnum.EMV_NO_CAPK_ERROR
import stone.application.enums.ErrorsEnum.EMV_TLV_ERROR
import stone.application.enums.ErrorsEnum.ERROR_RESPONSE_COMMAND
import stone.application.enums.ErrorsEnum.GENERIC_ERROR
import stone.application.enums.ErrorsEnum.INTERNAL_ERROR
import stone.application.enums.ErrorsEnum.INVALID_AMOUNT
import stone.application.enums.ErrorsEnum.INVALID_EMAIL_CLIENT
import stone.application.enums.ErrorsEnum.INVALID_STONECODE
import stone.application.enums.ErrorsEnum.INVALID_STONE_CODE_OR_UNKNOWN
import stone.application.enums.ErrorsEnum.INVALID_TRANSACTION
import stone.application.enums.ErrorsEnum.INVALID_TRANSACTION_STATUS
import stone.application.enums.ErrorsEnum.IO_ERROR_WITH_PINPAD
import stone.application.enums.ErrorsEnum.MIFARE_ABORTED
import stone.application.enums.ErrorsEnum.MIFARE_DETECT_TIMEOUT
import stone.application.enums.ErrorsEnum.MIFARE_INVALID_BLOCK_FORMAT
import stone.application.enums.ErrorsEnum.MIFARE_INVALID_BLOCK_NUMBER
import stone.application.enums.ErrorsEnum.MIFARE_INVALID_KEY
import stone.application.enums.ErrorsEnum.MIFARE_INVALID_SECTOR_NUMBER
import stone.application.enums.ErrorsEnum.MIFARE_MULTI_CARD_DETECTED
import stone.application.enums.ErrorsEnum.MIFARE_NOT_AUTHENTICATED
import stone.application.enums.ErrorsEnum.MIFARE_WRONG_CARD_TYPE
import stone.application.enums.ErrorsEnum.MULTI_INSTANCES_OF_PROVIDER_RUNNING
import stone.application.enums.ErrorsEnum.NEED_LOAD_TABLES
import stone.application.enums.ErrorsEnum.NOT_STONE_POS_OR_POS_MISCONFIGURED
import stone.application.enums.ErrorsEnum.NO_ACTIVE_APPLICATION
import stone.application.enums.ErrorsEnum.NO_MIFARE_SUPPORT
import stone.application.enums.ErrorsEnum.NO_PRINT_SUPPORT
import stone.application.enums.ErrorsEnum.NULL_RESPONSE
import stone.application.enums.ErrorsEnum.OPERATION_CANCELLED_BY_USER
import stone.application.enums.ErrorsEnum.PASS_TARGE_WITH_CHIP
import stone.application.enums.ErrorsEnum.PED_PASS_CRYPT_ERROR
import stone.application.enums.ErrorsEnum.PED_PASS_GENERIC_ERROR
import stone.application.enums.ErrorsEnum.PED_PASS_INIT_ERROR
import stone.application.enums.ErrorsEnum.PED_PASS_KEY_ERROR
import stone.application.enums.ErrorsEnum.PED_PASS_NO_KEY_FOUND_ERROR
import stone.application.enums.ErrorsEnum.PED_PASS_NO_PIN_INPUT_ERROR
import stone.application.enums.ErrorsEnum.PED_PASS_TIMEOUT_ERROR
import stone.application.enums.ErrorsEnum.PED_PASS_USER_CANCELED_ERROR
import stone.application.enums.ErrorsEnum.PINPAD_ALREADY_CONNECTED
import stone.application.enums.ErrorsEnum.PINPAD_CLOSED_CONNECTION
import stone.application.enums.ErrorsEnum.PINPAD_CONNECTION_NOT_FOUND
import stone.application.enums.ErrorsEnum.PINPAD_WITHOUT_KEY
import stone.application.enums.ErrorsEnum.PINPAD_WITHOUT_STONE_KEY
import stone.application.enums.ErrorsEnum.PRINTER_BUSY_ERROR
import stone.application.enums.ErrorsEnum.PRINTER_GENERIC_ERROR
import stone.application.enums.ErrorsEnum.PRINTER_INIT_ERROR
import stone.application.enums.ErrorsEnum.PRINTER_INVALID_DATA_ERROR
import stone.application.enums.ErrorsEnum.PRINTER_LOW_ENERGY_ERROR
import stone.application.enums.ErrorsEnum.PRINTER_OUT_OF_PAPER_ERROR
import stone.application.enums.ErrorsEnum.PRINTER_OVERHEATING_ERROR
import stone.application.enums.ErrorsEnum.PRINTER_UNSUPPORTED_FORMAT_ERROR
import stone.application.enums.ErrorsEnum.QRCODE_EXPIRED
import stone.application.enums.ErrorsEnum.QRCODE_NOT_GENERATED
import stone.application.enums.ErrorsEnum.SDK_VERSION_OUTDATED
import stone.application.enums.ErrorsEnum.SWIPE_INCORRECT
import stone.application.enums.ErrorsEnum.SWITCH_INTERFACE
import stone.application.enums.ErrorsEnum.TABLES_NOT_FOUND
import stone.application.enums.ErrorsEnum.TIME_OUT
import stone.application.enums.ErrorsEnum.TOO_MANY_CARDS
import stone.application.enums.ErrorsEnum.TRANSACTION_FALLBACK_INVALID_CARD_MODE
import stone.application.enums.ErrorsEnum.TRANSACTION_FALLBACK_STARTED
import stone.application.enums.ErrorsEnum.TRANSACTION_FALLBACK_TIMEOUT
import stone.application.enums.ErrorsEnum.TRANSACTION_NOT_FOUND
import stone.application.enums.ErrorsEnum.TRANSACTION_OBJECT_NULL_ERROR
import stone.application.enums.ErrorsEnum.TRANS_APP_BLOCKED_ERROR
import stone.application.enums.ErrorsEnum.TRANS_APP_INVALID_ERROR
import stone.application.enums.ErrorsEnum.TRANS_APP_INVALID_INDEX_ERROR
import stone.application.enums.ErrorsEnum.TRANS_CVV_INVALID_ERROR
import stone.application.enums.ErrorsEnum.TRANS_CVV_NOT_PROVIDED_ERROR
import stone.application.enums.ErrorsEnum.TRANS_GENERIC_ERROR
import stone.application.enums.ErrorsEnum.TRANS_INVALID_AMOUNT_ERROR
import stone.application.enums.ErrorsEnum.TRANS_NO_TRANS_TYPE_ERROR
import stone.application.enums.ErrorsEnum.TRANS_ONLINE_PROCESS_ERROR_ERROR
import stone.application.enums.ErrorsEnum.TRANS_PASS_MAG_BUT_IS_ICC_ERROR
import stone.application.enums.ErrorsEnum.TRANS_SELECT_TYPE_USER_CANCELED_ERROR
import stone.application.enums.ErrorsEnum.TRANS_WRONG_TRANS_TYPE_ERROR
import stone.application.enums.ErrorsEnum.UNEXPECTED_STATUS_COMMAND
import stone.application.enums.ErrorsEnum.UNKNOWN_ERROR
import stone.application.enums.ErrorsEnum.UNKNOWN_TYPE_OF_USER
import stone.application.enums.ErrorsEnum.USERMODEL_NOT_FOUND
import stone.providers.BaseProvider


fun BaseProvider.getMessageOfErrorInPortuguese(): String {
    return this.listOfErrors.joinToString("\n") { getMessageFromEnum(it) }
}

fun getMessageFromEnum(enum: ErrorsEnum): String {
    return when (enum) {
        CARD_BLOCKED -> "Cartão bloqueado"
        CONNECTION_NOT_FOUND -> "Sem conexão com a internet, ligue a internet"
        DEVICE_NOT_COMPATIBLE -> "Dispositivo não compatível"
        UNEXPECTED_STATUS_COMMAND -> "Status de um comando inesperado, tente novamente"
        IO_ERROR_WITH_PINPAD -> "Erro de leitura e escrita com o Pinpad"
        PINPAD_CONNECTION_NOT_FOUND -> "Sem conexão com um Pinpad, conecte-se com um dispositivo"
        TABLES_NOT_FOUND -> "Tabela não encontrada"
        NEED_LOAD_TABLES -> "Precisa carregar as tabelas"
        TIME_OUT -> "Tempo expirado, tente novamente"
        OPERATION_CANCELLED_BY_USER -> "Operação cancelada pelo usuário"
        CARD_REMOVED_BY_USER -> "Cartão removido pelo usuário"
        CANT_READ_CARD_HOLDER_INFORMATION -> "Erro na leitura das informações do cartão, tente novamente"
        INVALID_TRANSACTION -> "Transação inválida, talvez um cartão de crédito esteja passando uma transação de débito, ou vice-versa"
        PASS_TARGE_WITH_CHIP -> "Cartão de chip passou tarja"
        NULL_RESPONSE -> "Não houve resposta do Pinpad"
        ERROR_RESPONSE_COMMAND -> "Erro na resposta do comando. Tente novamente"
        ACCEPTOR_REJECTION -> "Transação rejeitada pelo autorizador"
        EMAIL_MESSAGE_ERROR -> "Erro no envio do e-mail"
        INVALID_EMAIL_CLIENT -> "E-mail do cliente é inválido"
        EMAIL_EMPTY, EMAIL_RECIPIENT_EMPTY -> "E-mail do cliente está vazio"
        INVALID_STONE_CODE_OR_UNKNOWN, INVALID_STONECODE -> "Código Stone inválido ou desconhecido"
        TRANSACTION_NOT_FOUND -> "Não se pode cancelar uma transação que não foi aprovada"
        INVALID_TRANSACTION_STATUS -> "Transação com status inválido"
        USERMODEL_NOT_FOUND -> "Usuário não encontrado"
        NO_PRINT_SUPPORT -> "Impressão não suportada"
        CANT_READ_CHIP_CARD -> "O Pinpad não consegue ler o chip do cartão"
        PINPAD_WITHOUT_KEY, PINPAD_WITHOUT_STONE_KEY -> "O Pinpad não tem a chave"
        PINPAD_ALREADY_CONNECTED -> "Pinpad já conectado"
        CONNECTIVITY_ERROR -> "Erro de conectividade"
        SWIPE_INCORRECT -> "Erro de passagem do cartão"
        TOO_MANY_CARDS -> "Muitos cartões detectados"
        UNKNOWN_ERROR -> "Erro desconhecido"
        INTERNAL_ERROR -> "Erro interno"
        EMV_GENERIC_ERROR -> "Erro genérico de Smartcard"
        SWITCH_INTERFACE -> "Interface (Contactless, Contato) não é válida para essa operação, necessário trocar"
        EMV_FAILED_CARD_CONN_ERROR -> "Erro de conexão com o cartão"
        EMV_NO_APP_ERROR -> "Nenhuma aplicação compatível (Crédito/Débito) disponível no cartão"
        EMV_INITIALIZATION_ERROR -> "Erro ao inicializar fluxo de pagamento"
        EMV_CAPK_ERROR -> "Erro ao configurar chave pública da bandeira"
        EMV_TLV_ERROR -> "Erro no formato (TLV) dos dados de configuração"
        EMV_NO_CAPK_ERROR -> "Chave pública da bandeira não está presente"
        EMV_AID_ERROR -> "Erro ao configurar aplicação (Crédito/Débito)"
        PRINTER_GENERIC_ERROR -> "Erro genérico de impressão"
        PRINTER_BUSY_ERROR -> "Erro de impressão, impressora ocupada"
        PRINTER_INIT_ERROR -> "Erro ao inicializar impressora"
        PRINTER_LOW_ENERGY_ERROR -> "Erro de impressão, pouca energia"
        PRINTER_OUT_OF_PAPER_ERROR -> "Erro de impressão, sem papel"
        PRINTER_UNSUPPORTED_FORMAT_ERROR -> "Erro de impressão, formato não suportado"
        PRINTER_INVALID_DATA_ERROR -> "Erro de impressão, dados inválidos"
        PRINTER_OVERHEATING_ERROR -> "Erro de impressão, superaquecimento"
        PED_PASS_GENERIC_ERROR -> "Erro genérico na criptografia do PIN"
        PED_PASS_KEY_ERROR -> "Erro de chave"
        PED_PASS_USER_CANCELED_ERROR -> "Erro de cancelamento do usuário"
        PED_PASS_NO_PIN_INPUT_ERROR -> "Erro de entrada de PIN"
        PED_PASS_TIMEOUT_ERROR -> "Erro "
        PED_PASS_INIT_ERROR -> "Erro na inicialização do processo de criptografia do PIN"
        PED_PASS_CRYPT_ERROR -> "Erro de criptografia do PIN"
        PED_PASS_NO_KEY_FOUND_ERROR -> "Erro de chave não encontrada"
        TRANS_GENERIC_ERROR -> "Erro genérico de transação"
        TRANS_APP_BLOCKED_ERROR -> "Erro de transação, aplicação bloqueada"
        TRANS_SELECT_TYPE_USER_CANCELED_ERROR -> "Erro de transação ao selecionar o tipo, cancelado pelo usuário"
        TRANS_INVALID_AMOUNT_ERROR -> "Erro de transação, valor inválido"
        TRANS_PASS_MAG_BUT_IS_ICC_ERROR -> "Erro de transação, passou tarja mas é chip"
        TRANS_NO_TRANS_TYPE_ERROR -> "Erro de transação, tipo de transação não encontrado"
        TRANS_WRONG_TRANS_TYPE_ERROR -> "Erro de transação, tipo de transação errado"
        TRANS_APP_INVALID_ERROR -> "Aplicação selecionada (Crédito/Débito) é inválida para esse fluxo"
        TRANS_CVV_NOT_PROVIDED_ERROR -> "CVV é obrigatório para esta transação e não foi provido"
        TRANS_CVV_INVALID_ERROR -> "CVV é inválido"
        TRANS_APP_INVALID_INDEX_ERROR -> "Erro ao gerar os dados para processar a transação junto ao autorizador"
        TRANS_ONLINE_PROCESS_ERROR_ERROR -> "Erro ao gerar os dados para processar a transação junto ao autorizador"
        CARD_GENERIC_ERROR -> "Erro genérico no cartão"
        CARD_READ_ERROR -> "Erro na leitura do cartão"
        CARD_READ_TIMEOUT_ERROR -> "Erro de tempo na leitura do cartão"
        CARD_READ_CANCELED_ERROR -> "Erro de cancelamento na leitura do cartão"
        CARD_UNSUPPORTED_ERROR -> "Erro de cartão não suportado"
        CARD_READ_MULTI_ERROR -> "Erro de leitura múltipla do cartão"
        TRANSACTION_FALLBACK_STARTED -> "Transação de fallback iniciada"
        TRANSACTION_FALLBACK_TIMEOUT -> "Tempo de fallback expirado"
        TRANSACTION_FALLBACK_INVALID_CARD_MODE -> "Transação de fallback com modo de cartão inválido"
        NO_ACTIVE_APPLICATION -> "Nenhuma aplicação ativa"
        MULTI_INSTANCES_OF_PROVIDER_RUNNING -> "Múltiplas instâncias do provider ativas"
        UNKNOWN_TYPE_OF_USER -> "Erro desconhecido do usuário"
        TRANSACTION_OBJECT_NULL_ERROR -> "Transação nula"
        INVALID_AMOUNT -> "Erro de valor inválido"
        APPNAME_NOT_SET -> "Nome do aplicativo não definido"
        SDK_VERSION_OUTDATED -> "Versão do SDK desatualizada"
        PINPAD_CLOSED_CONNECTION -> "Conexão com o Pinpad fechada"
        NOT_STONE_POS_OR_POS_MISCONFIGURED -> "Não é um Stone POS ou POS mal configurado"
        COULD_NOT_ACTIVATE_ALL_STONE_CODES -> "Não foi possível ativar todos os códigos Stone"
        COULD_NOT_ACTIVATE_WITH_ACCEPTOR_CONFIGURATION_UPDATE_DATA_NULL -> "Não foi possível ativar com dados de atualização de configuração do adquirente nulos"
        QRCODE_NOT_GENERATED -> "Erro na geração do QRCode"
        QRCODE_EXPIRED -> "QRCode expirado"
        NO_MIFARE_SUPPORT, MIFARE_ABORTED, MIFARE_DETECT_TIMEOUT, MIFARE_WRONG_CARD_TYPE, MIFARE_INVALID_KEY, MIFARE_NOT_AUTHENTICATED, MIFARE_INVALID_SECTOR_NUMBER, MIFARE_INVALID_BLOCK_NUMBER, MIFARE_INVALID_BLOCK_FORMAT, MIFARE_MULTI_CARD_DETECTED -> "Erro de Mifare"
        DATA_CONTAINER_CONSTRAINT_ERROR -> "Erro desconhecido"
        DATA_CONTAINER_INTEGRATION_ERROR -> TODO()
        GENERIC_ERROR -> "Erro genérico"
    }+ " (${enum.name})."
}