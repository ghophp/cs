<?php

include 'helper/resource.php';

function bigSorting($arr) {
    for ($x = count($arr) - 1; $x >= 0; $x--) {
    	for ($i = $x - 1; $i >= 0; $i--) {
    		if (isBiggerThan($arr[$i], $arr[$x])) {
				$hold = $arr[$i];

	    		$arr[$i] = $arr[$x];
	    		$arr[$x] = $hold;
    		}
    	}
    }

    return $arr;
}

function isBiggerThan($a, $b) {
	if (strlen($a) > strlen($b)) {
		return true;
	} else if (strlen($a) === strlen($b)) {
		for ($x = 0; $x < strlen($a); $x++) {
			$aN = intval($a[$x]);
			$bN = intval($b[$x]);

			if ($aN > $bN) {
				return true;
			} else if ($aN === $bN) {
				continue;
			} else {
				return false;
			}
		}
	}

	return false;
}

$testCases = [
    [
    	'in' => ["6", "31415926535897932384626433832795", "1", "3", "10", "3", "5"],
    	'out' => ["1", "3", "3", "5", "6", "10", "31415926535897932384626433832795"],
    ],
    [
    	'in' => read_int_resource('big-sorting/big-sorting.txt'),
    	'out' => read_int_resource('big-sorting/big-sorting-result.txt'),
    ]
];

foreach ($testCases as $testCase) {
	$input = $testCase['in'];
	$expects = $testCase['out'];

    $result = bigSorting($input);
    echo sprintf("test with input %s expects %s: %s\n", print_r($input, true), print_r($expects, true), $expects === $result ? 'PASS': 'FAIL');
}

?>
