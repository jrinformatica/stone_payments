import 'package:json_annotation/json_annotation.dart';

@JsonEnum(valueField: "index")
enum TypeTransactionEnum {
  debit("Débito"),
  credit("Crédito"),
  voucher("Voucher"),
  instantPayment("Pagamento Instantâneo"),
  pix("Pix");

  final String nome;
  const TypeTransactionEnum(this.nome);
}
