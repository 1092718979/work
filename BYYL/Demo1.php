<?php

class Word{
    public $point = [
        1 => [
            'main','int','float','double','char','if','elst','do','while'
        ],
        2 => [
            '=','+','-','*','/','(',')','{','}','>','>=','<','<=','==','!='
        ],
        3 => [
            ',',';','#'
        ],
    ];
    public $pointNum = [
        1 => [1,2,3,4,5,6,7,8,9],
        2 => [21,22,23,24,25,26,27,28,29,32,33,34,35,36,37],
        3 => [30,31,100],
        4 => [10],
        5 => [21],
    ];
    public $class = [
        'keyword' => 1,
        'operator' => 2,
        'delimiter' => 3,
        'identifier' => 4,      //标识符
        'constant' => 5,        //常量
    ];
    public $special = [
        'first' => ['<','>','!','='],
    ];
    //拼接字符串
    public $line = '';
    //拼接数字
    public $numberLine = '';
    //记录小数点
    public $radixPoint = 0;
    //文件字符串数组
    public $wordArr;
    //记录上一位的类型 1 符号 2 字母 3 界符 4 数字 5 空格 0 初始化
    public $prior = 0;
    //记录上一位特殊符号
    public $char;
    //返回结果集
    private $result = [];
    private $resultNumber = [];
    private $resultType = [];

    function __construct($arr) {
        $this->wordArr = $arr;
    }

    /**
     * 运行主程序
     */
    public function run(){
        foreach ($this->wordArr as $value){
            $this->wordAnalysis($value);
        }
    }

    /**
     * 辨别字符的种类，根据种类处理
     * @param $value
     * @return string
     */
    private function wordAnalysis($value){
        if (('a' <= $value && $value <= 'z') || ('A' <= $value && $value <= 'Z')){
            $this->letter($value);
        }else if('0' <= $value && $value <= '9' || $value == '.'){
            $this->number($value);
        }else if ((in_array($value,$this->point[2])) || (in_array($value,$this->point[3]))){
            $this->symbol($value);
        }else if($value == ' '){
           $this->space();
        }else{
            return '存在不合法的字符';
        }
        return 0;
    }

    /**
     * 处理字母
     */
    private function letter($value){
        if ($this->prior == 4 && $value == 'e'){
            $this->numberLine .=$value;
            $this->allPush($this->numberLine,$this->class['constant'],0);
            $this->numberLine = '';
            $this->radixPoint = 0;
            $this->prior = 5;
        }else{
            $this->line .= $value;
            $this->prior = 2;
        }
    }

    /**
     * 处理数字
     */
    private function number($value){
        if ($this->prior == 2){
            $this->line .= $value;
        }else{
            if ($value == '.'){
                if ($this->radixPoint == 0){
                    $this->radixPoint = 1;
                }else{
                    echo "小数定义错误";
                    exit();
                }
            }
            $this->numberLine .= $value;
            $this->prior = 4;
        }
    }

    /**
     * 运算符或界符的处理
     */
    private function symbol($value){
        //运算符处理
        if (in_array($value,$this->point[2])){
            if ($this->prior != 1){
                $this->finish();
                if (in_array($value,$this->special['first'])){
                    $this->char = $value;
                }else{
                    $type = $this->getSubscript($value,$this->point[2]);
                    $this->allPush($value,$this->class['operator'],$type);
                }
                $this->prior = 1;

            }else{
                if (empty($this->char)){
                    if (in_array($value,$this->special['first'])){
                        $this->char = $value;
                    }else{
                        $type = $this->getSubscript($value,$this->point[2]);
                        $this->allPush($value,$this->class['operator'],$type);
                    }
                }else{
                    $check = $this->char.$value;
                    if (in_array($check,$this->point[2])){
                        $type = $this->getSubscript($check,$this->point[2]);
                        $this->allPush($check,$this->class['operator'],$type);
                        $this->char = '';
                    }
                }
                $this->prior = 1;
            }
        }
        //界符处理
        if (in_array($value,$this->point[3])){
            $this->finish();
            $type = $this->getSubscript($value,$this->point[3]);
            $this->allPush($value,$this->class['delimiter'],$type);
            $this->prior = 3;
        }
    }

    /**
     * 空格处理
     */
    private function space(){
        $this->finish();
        $this->prior = 5;
    }

    /**
     * 识别到单个可结束的字符
     */
    public function finish(){
        switch ($this->prior){
            case 1:
                if (!empty($this->char)){
                    $type = $this->getSubscript($this->char,$this->point[2]);
                    $this->allPush($this->char,$this->class['operator'],$type);
                    $this->char = '';
                }
                break;
            case 2:
                $this->pushString();
                break;
            case 3:
                break;
            case 4:
                $this->allPush($this->numberLine,$this->class['constant'],0);
                $this->numberLine = '';
                $this->radixPoint = 0;
                break;
            case 5:
                break;
        }
    }

    /**
     * 对结束的字符串进行处理
     */
    public function pushString(){
        if (in_array($this->line,$this->point[1])){
            $type = $this->getSubscript($this->line,$this->point[1]);
            $this->allPush($this->line,$this->class['keyword'],$type);
            $this->line = '';
        }else{
            $this->allPush($this->line,$this->class['identifier'],0);
            $this->line = '';
        }
    }

    /**
     * 遍历数组找到key在array中的下标
     */
    public function getSubscript($key,$array){
        for($i = 0 ; $i <= count($array) ; $i++){
            if ($key == $array[$i]){
                return $i;
            }
        }
    }

    /**
     * 对识别出的'词'进行保存
     * @param $value        词本身
     * @param $valueNum     词的类型
     * @param $type         编码类型所对应的数组下标——种别编码
     */
    public function allPush($value,$valueNum,$type){
        array_push($this->result,$value);
        array_push($this->resultNumber,$valueNum);
        array_push($this->resultType,$this->pointNum[$valueNum][$type]);
    }

    public function getResult(){
        return $this->result;
    }

    public function getResultNumber(){
        return $this->resultNumber;
    }

    public function getResultType(){
        return $this->resultType;
    }
}
?>