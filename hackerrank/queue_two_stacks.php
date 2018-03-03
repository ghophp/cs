<?php

const QUERY_READ = 3;
const QUERY_INSERT = 1;
const QUERY_DELETE = 2;

class Queue 
{
    private $stack;    
    
    public function __construct($size, $head = null) {
        $this->stack = array();
        $this->size = $size;
    }
    
    public function queue($value) {
        array_push($this->stack, $value);
    }

    public function dequeue() {
        array_shift($this->stack);
    }

    public function peek() {
        if (!count($this->stack)) {
            return null;
        }
        return current($this->stack);
    }
    
    public function size() {
        return count($this->stack);
    }
}
   
$_fp = fopen("php://stdin", "r");

$queueSize = fgets($_fp);
$queue = new Queue($queueSize);

for ($x = 0; $x < $queueSize; $x++) {
    list($query, $value) = fscanf($_fp, "%d %d\n");
    
    if ($query === QUERY_INSERT) {
        $queue->queue(intval($value));
    } else if ($query === QUERY_DELETE) {
        $queue->dequeue();
    } else if ($query === QUERY_READ) {
        echo $queue->peek() . "\n";
    }
}

?>