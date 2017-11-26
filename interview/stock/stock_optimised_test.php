<?php

include_once 'stock_optimised.php';

$testSets = [
	[
		'values' => [
			1 => 10,
			2 => 10,
			3 => 10,
			4 => 5,
			5 => 5,
		],
		'expected' => 0,
	],
	[
		'values' => [
			1 => 10,
			2 => 10,
			3 => 10,
			4 => 15,
			5 => 20,
		],
		'expected' => 10,
	],
	[
		'values' => [
			1 => 5,
			2 => 10,
			3 => 15,
			4 => 5,
			5 => 95,
			6 => 100
		],
		'expected' => 95,
	],
];

foreach ($testSets as $index => $testSet) {
	$testResult = $testSet['expected'] === getMaximumProfit($testSet['values']);
	echo sprintf("Test %d with result: %s\n", $index, $testResult ? 'PASS' : 'FAIL');
}