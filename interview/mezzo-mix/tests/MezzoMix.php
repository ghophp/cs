<?php

declare(strict_types=1);

include_once join(DIRECTORY_SEPARATOR, [__DIR__, "..", "src", "MezzoMix.php"]);

use MezzoMix\MezzoMix;

const PASS = "\033[32mPASS\033[0m";
const FAIL = "\033[31mFAIL\033[0m";

$testCases = [
	3  => '3 Cola',
	5  => '5 Fanta',
	6  => '6 Cola',
	15 => '15 Mezzo Mix',
	16 => '16',
	30 => '30 Mezzo Mix',
	43 => '43',
];

$mezzoMix = new MezzoMix();

foreach($testCases as $input => $expects) {
	$formatted = $mezzoMix->formatNumberWithSodaType($input);
	print sprintf("Input %d expects %s: %s\n", 
		$input, 
		$expects, 
		$expects === $formatted ? PASS : FAIL);
}

?>