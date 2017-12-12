<?php

const AM = "AM";
const PM = "PM";

function timeConversion($s) {
    $charN = count($s);

    $hour = intval(substr($s, 0, 2));
    $minutes = substr($s, 3, 2);
    $seconds = substr($s, 6, 2);
    $period = substr($s, 8, 2);
    
    if ($hour === 12) {
        if ($period === AM) {
            $hour = 0;
        }    
    } else if ($period === PM) {
        $hour = $hour + 12;
    }
    
    return sprintf("%'.02d:%s:%s", $hour, $minutes, $seconds);
}

$testCases = [
    '07:00:00PM' => '19:00:00',
    '12:15:00PM' => '12:15:00',
    '12:50:20AM' => '00:50:20',
    '01:01:02PM' => '13:01:02',

];

foreach ($testCases as $input => $expects) {
    $result = timeConversion($input);
    echo sprintf("test with input %s expects %s: %s\n", $input, $expects, $expects === $result ? 'PASS': 'FAIL');
}

?>
