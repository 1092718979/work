<?php

/**
 * Created by JiFeng.
 * User: 10927
 * Date: 2018/6/21
 * Time: 15:08
 */
class Sentence {
    public $result;
    public $type;
    public $num;
    public $token;
    public function __construct($result,$type,$num) {
        $this->result = $result;
        $this->type = $type;
        $this->num = $num;
        $last = count($this->result);
        unset($this->result[$last-1]);
    }

    public function run(){
        $parsing = $this->group($this->integration());
        $this->expression($parsing);
        return $this->token;
    }

    /**
     * 对符号就行合并
     */
    public function integration(){
        $res = $this->result;
        for ($i = 2;$i <count($res);$i++){
            if (in_array($res[$i-2],['+','-','*','/'])){
                if (in_array($res[$i-1],['+','-'])){
                    $res[$i-1] .=$res[$i];
                    $res[$i] = '$';
                }
            }
        }
        foreach ($res as $key => $value){
            if ($value == '$'){
                unset($res[$key]);
            }
        }
       $rest = [];
        $i = 0;
        foreach ($res as $value){
            $rest[$i] = $value;
            $i++;
        }
        return $rest;
    }

    /**
     * 对括号成员进行分组
     */
    public function group($res){
        $group = '';
        $hava = 0;
        $i = 0;
        foreach ($res as $key=>$value){
            if ($value == '(') {
                $group[$i] = ['('];
                $hava = 1;
            }
            elseif($value == ')'){
                array_push($group[$i], $value);
                $hava =0;
                $i++;
            }
            elseif($hava == 1){
                array_push($group[$i], $value);
            }
            else{
                $group[$i] = $value;
                $i++;
            }
        }
        return $group;
    }

    /**
     * 表达式
     */
    public function expression($parsing){
        $point = '';
        foreach ($parsing as $key=>$value){
            if ($value == '+' || $value == '-'){
                $point = $key;
                break;
            }
        }
        if (empty($point)){
            $this->item($parsing);
        }else{
            $array1 = array_slice($parsing,0,$point);
            $array2 = array_slice($parsing,$point+1,(count($parsing)-1));
            $this->item($array1);
            $this->item($array2);
        }
    }

    /**
     * 项
     */
    public function item($parsing){
        $point = '';
        foreach ($parsing as $key=>$value){
            if ($value == '*' || $value == '/'){
                $point = $key;
                break;
            }
        }
        if (empty($point)){
            $this->factor($parsing);
        }else{
            $array1 = array_slice($parsing,0,$point);
            $array2 = array_slice($parsing,$point+1,(count($parsing)-1));
            $this->factor($array1);
            $this->factor($array2);
        }
    }

    /**
     * 因子
     */
    public function factor($parsing){
        $cou = count($parsing);
        if ($cou == 1){
            $couPars = count($parsing[0]);
            if ($couPars == 1){
                $line = $parsing[0];
                if (substr($line,0,1) == '+' || substr($line,0,1) == '-') {
                    $line = substr($line, 1);
                    foreach ($this->result as $key => $value) {
                        if ($line == $value) {
                            if ($this->type[$key] != 5) {
                                $this->token = 'error';
                                return 0;
                            }
                        }
                    }
                }
            }else{
                $c = count($parsing[0])-1;
                unset($parsing[0][$c]);
                unset($parsing[0][0]);
                $pars = [];
                $i = 0;
                foreach ($parsing[0] as $value){
                    $pars[$i] = $value;
                    $i++;
                }
                $this->expression($pars);
            }
        }else{
                $this->expression($parsing);
        }
        $this->token = 'success';
    }
}