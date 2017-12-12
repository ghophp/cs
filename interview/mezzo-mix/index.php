<?php

declare(strict_types=1);

include_once join(DIRECTORY_SEPARATOR, [__DIR__, "src", "MezzoMix.php"]);

use MezzoMix\MezzoMix;

/**
 * Iterate from 0..100 and uses the @see MezzoMix class to format
 * the current number in the loop
 * 
 * @param  MezzoMix
 * @return void
 */
function run(MezzoMix $mezzoMix) 
{
	for ($x = 0; $x <= 100; $x++) {
		print $mezzoMix->formatNumberWithSodaType($x) . "\n";
	}
}

run(new MezzoMix());

?>