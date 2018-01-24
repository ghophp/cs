<?php

function read_int_resource($filename) {
	$arr = [];

	$file_handle = fopen(__DIR__ . '/../resources/' . $filename, "r");
	if (!$file_handle) {
		return $arr;
	}

	while (!feof($file_handle) ) {
		$line_of_text = fgets($file_handle);
		$arr[] = trim($line_of_text);
	}

	fclose($file_handle);
	return $arr;
}

?>