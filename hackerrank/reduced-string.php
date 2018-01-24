<?php

const EMPTY_STRING = 'Empty String';

function super_reduced_string($s) {
	$current = str_split($s);
	$x = 0;

	while ($x < count($current) - 1) {
		if ($current[$x] === $current[$x + 1]) {
			unset($current[$x]);
			unset($current[$x + 1]);

			if (count($current) <= 0) {
				return EMPTY_STRING;
			}

			$s = implode("", $current);
			$current = str_split($s);
			$x = 0;

			continue;
		}

		$x++;
	}

	return implode("", $current);
}

$testCases = [
    [
    	'in' => 'aaabccddd',
    	'out' => 'abd',
    ],
    [
    	'in' => 'baab',
    	'out' => EMPTY_STRING
    ]
];

foreach ($testCases as $testCase) {
	$input = $testCase['in'];
	$expects = $testCase['out'];

    $result = super_reduced_string($input);
    echo sprintf("test with input %s expects %s: %s\n", $input, $expects, $expects === $result ? 'PASS': 'FAIL');
}

?>
