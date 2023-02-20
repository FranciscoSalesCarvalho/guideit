import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:nock/nock.dart';
import 'package:stock/pages/information_page.dart';
import 'package:stock/widgets/error.dart';
import 'package:stock/widgets/loading.dart';

void main() {
  setUpAll(nock.init);

  setUp(() {
    nock.cleanAll();
  });

  testWidgets('should display the table', (WidgetTester tester) async {
    nock('query2.finance.yahoo.com').get('/v8/finance/chart/PETR4.SA')
      ..query({'interval': '1d', 'range': '1mo'})
      ..persist()
      ..reply(200, readJson())
      ..onReply(() async {
        await tester.pumpWidget(const MaterialApp(
          home: InformationPage(),
        ));
        expect(find.byType(StockLoading), findsOneWidget);
        expect(find.text('Stock Information'), findsOneWidget);

        await tester.pumpAndSettle();
        expect(find.byType(StockLoading), findsNothing);
        expect(find.byType(Table), findsOneWidget);
      });
  });
}

Future<void> readJson() async {
  final String response = await rootBundle.loadString('assets/mock.json');
  return await json.decode(response);
}
