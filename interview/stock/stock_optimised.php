<?php

/**
 * This challenge was presented in one of the interviews in Berlin.
 */

/**
 * The following array contains the days of the week and the stock value
 * for the day, the focus is to find the maximum profit, meaning that
 * from day N to N+(N+1) what is the biggest different you can get
 * 
 * @var [type]
 */
$stockValues = [
	1 => 130,
	2 => 110,
	3 => 90,
	4 => 125,
	5 => 85,
	6 => 100
];

/** 
 * @param  array $stockValues each entry contains a day => stock value
 * 
 * @return int the maximum profit for the stock set
 */
function getMaximumProfit($stockValues) 
{
	if (count($stockValues) <= 1) {
		return 0;
	}

	$maxProfit = 0;
	$minDay = -1;
	$minValue = PHP_INT_MAX;

	foreach ($stockValues as $day => $stockValue) {
		if ($stockValue < $minValue) {
			$minDay = $day;
			$minValue = $stockValue;

			continue;
		}
		if ($stockValue > $minValue) {
			$diff = ($stockValue - $minValue);
			if ($diff > $maxProfit) {
				$maxProfit = $diff;
			}
		}
	}

	return $maxProfit;
}

var_dump(getMaximumProfit($stockValues));
