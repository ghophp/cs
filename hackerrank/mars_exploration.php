<?php

function marsExploration($s) {
	$helpWord = ['S', 'O', 'S'];
    $helpWordSize = count($helpWord);
    
    $modified = 0;
    
    for ($x = 0; $x < strlen($s); $x = $x + $helpWordSize) {
    	$chunk = substr($s, $x, $helpWordSize);
    	if ($chunk !== implode('', $helpWord)) {
    		for ($i = 0; $i < strlen($chunk); $i++) {
    			if ($chunk[$i] !== $helpWord[$i]) {
    				$modified++;
    			}
    		}	
    	}
    }

    return $modified;
}

$testCases = [
    [
    	'in' => 'SOSSPSSQSSOR',
    	'out' => 3,
    ],
    [
    	'in' => 'SOSSOT',
    	'out' => 1
    ],
    [
    	'in' => 'SOSSOSSOS',
    	'out' => 0
    ],
    [
    	'in' => 'SOSOOSOSOSOSOSSOSOSOSOSOSOS',
    	'out' => 12
    ]
];

foreach ($testCases as $testCase) {
	$input = $testCase['in'];
	$expects = $testCase['out'];

	$result = marsExploration($input);
    echo sprintf("test with input %s expects %s got %d: %s\n", $input, $expects, $result, $expects === $result ? 'PASS': 'FAIL');
}

?>