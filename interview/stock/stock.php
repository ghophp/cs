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
	$stockCount = count($stockValues);
	$maximumProfit = 0;

	for ($i = 1; $i < $stockCount; $i++) {
		for ($x = $i + 1; $x < $stockCount; $x++) {
			$currentDay = $stockValues[$i];
			$nextDay = $stockValues[$x];

			if ($currentDay > $nextDay) {
				continue;
			}

			$diff = $nextDay - $currentDay;
			if ($diff > $maximumProfit) {
				$maximumProfit = $diff;
			}
		}
	}

	return $maximumProfit;
}

var_dump(getMaximumProfit($stockValues));
