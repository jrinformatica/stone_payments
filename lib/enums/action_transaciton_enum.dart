import 'package:json_annotation/json_annotation.dart';

@JsonEnum(fieldRename: FieldRename.screamingSnake)
enum ActionTransacitonEnum {
  transactionWaitingCard, //	Aguardando o cartão ser inserido
  transactionWaitingPassword, //	Aguardando a senha do cartão
  transactionSending, //	Enviando a transação para a Stone
  transactionRemoveCard, //	Aguardando o cartão ser retirado (Somente para Chip e Senha)
  transactionCardRemoved, //	Indica que o cartão foi removido
  reversingTransactionWithError, //	Tentando reverter transação com status WITH_ERROR
  transactionTypeSelection, //	Usado quando necessário escolher entre modalidades. Por exemplo: Alimentação e Refeição no mesmo cartão
  transactionWaitingQrcodeScan, //	Usado para mostrar o QrCode e aguardar sua leitura
}

const Map<String, ActionTransacitonEnum> actionTransactionEnumMap =
    <String, ActionTransacitonEnum>{
  'TRANSACTION_WAITING_CARD': ActionTransacitonEnum.transactionWaitingCard,
  'TRANSACTION_WAITING_PASSWORD':
      ActionTransacitonEnum.transactionWaitingPassword,
  'TRANSACTION_SENDING': ActionTransacitonEnum.transactionSending,
  'TRANSACTION_REMOVE_CARD': ActionTransacitonEnum.transactionRemoveCard,
  'TRANSACTION_CARD_REMOVED': ActionTransacitonEnum.transactionCardRemoved,
  'REVERSING_TRANSACTION_WITH_ERROR':
      ActionTransacitonEnum.reversingTransactionWithError,
  'TRANSACTION_TYPE_SELECTION': ActionTransacitonEnum.transactionTypeSelection,
  'TRANSACTION_WAITING_QRCODE_SCAN':
      ActionTransacitonEnum.transactionWaitingQrcodeScan,
};
