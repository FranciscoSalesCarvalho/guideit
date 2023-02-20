import 'package:flutter/material.dart';

class StockError extends StatelessWidget {

  final Object? errorMessage;

  const StockError(this.errorMessage, {Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            const Icon(
              Icons.error_outline,
              color: Colors.red,
              size: 60,
            ),
            Padding(
              padding: const EdgeInsets.only(top: 16),
              child: Text('Error: $errorMessage}'),
            ),
          ],
        ));
  }
}
