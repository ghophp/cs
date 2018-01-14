<?php

function fibonacci($n) {
    if ($n === 0 || $n === 1) {
    	return $n;
    }

    return fibonacci($n - 2) + fibonacci($n - 1);
}

$handle = fopen ("php://stdin","r");

$n = fgets($handle);

printf("%d", fibonacci($n));

fclose($handle);

?>
