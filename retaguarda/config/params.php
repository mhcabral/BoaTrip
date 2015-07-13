<?php
use kartik\datecontrol\Module;
return [
    'adminEmail' => 'apsb@icomp.ufam.edu.br',
	'maskMoneyOptions' => [
			'prefix' => 'R$ ',
			'suffix' => '',
			'affixesStay' => true,
			'thousands' => '.',
			'decimal' => ',',
			'precision' => 2,
			'allowZero' => false,
			'allowNegative' => false,
	],
	// format settings for displaying each date attribute (ICU format example)
	'dateControlDisplay' => [
			Module::FORMAT_DATE => 'dd/MM/yyyy',
			Module::FORMAT_TIME => 'HH:mm:ss a',
			Module::FORMAT_DATETIME => 'dd/MM/yyyy HH:mm:ss a',
	],
	
	// format settings for saving each date attribute (PHP format example)
	'dateControlSave' => [
			Module::FORMAT_DATE => 'php:Y-m-d', // saves as unix timestamp
			Module::FORMAT_TIME => 'php:H:i:s',
			Module::FORMAT_DATETIME => 'php:Y-m-d H:i:s',
	]
];
