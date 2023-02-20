import 'package:intl/intl.dart';

const String brazilianDateFormat = "dd/MM/yyyy";

extension DateParsing on int {
  String toBrazilianDate() {
    return DateFormat(brazilianDateFormat).format(
      DateTime.fromMillisecondsSinceEpoch(this * 1000),
    );
  }
}

extension MoneyParsing on num? {
  String toBrazilianMoney() {
    return "R\$ ${NumberFormat("#,##0.00", "pt_BR").format(this ?? 0.0)}";
  }
}
