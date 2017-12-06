<?php

const NO = "NO";
const YES = "YES";

function checkUnreacheable($kangoroo1, $step1, $kangoroo2, $step2) {
    if ($kangoroo1 > $kangoroo2 && $step1 >= $step2) {
        return true;
    } else if ($kangoroo2 > $kangoroo1 && $step2 >= $step1) {
        return true;
    }
    
    return false;
}

function kangaroo($kangoroo1, $step1, $kangoroo2, $step2) {
    if (checkUnreacheable($kangoroo1, $step1, $kangoroo2, $step2)) {
        return NO;
    }
    
    while (true) {
        if ($kangoroo1 === $kangoroo2) {
            return YES;
        }
        
        $kangoroo1 += $step1;
        $kangoroo2 += $step2;
        
        if (checkUnreacheable($kangoroo1, $step1, $kangoroo2, $step2)) {
            return NO;
        }   
    }
    
    return NO;
}

$testCases = [
    [
    	'in' => [0, 3, 4, 2],
    	'out' => YES,
    ],
    [
    	'in' => [0, 2, 5, 3],
    	'out' => NO
    ]
];

foreach ($testCases as $testCase) {
	$input = $testCase['in'];
	$expects = $testCase['out'];

    $result = call_user_func_array("kangaroo", $input);
    echo sprintf("test with input %s expects %s: %s\n", $input, $expects, $expects === $result ? 'PASS': 'FAIL');
}

?>
