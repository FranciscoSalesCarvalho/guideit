import 'dart:convert';

import 'package:http/http.dart' as http;

import '../model/stock.dart';

class StockService {

  Future<Stock> getInfo(String stock) async {
    Map<String, dynamic> query = {'interval': '1d', 'range': '1mo'};

    final url = Uri.https('query2.finance.yahoo.com',
        '/v8/finance/chart/$stock', query);
      final response = await http.get(url);
      
      if (response.statusCode != 200) {
        throw Exception();
      }

      final extractedData = json.decode(response.body) as Map<String, dynamic>;
      return Stock.fromJson(extractedData);
  }
}