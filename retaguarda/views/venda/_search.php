<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;
use kartik\datecontrol\DateControl;

/* @var $this yii\web\View */
/* @var $model app\models\VendaSearch */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="venda-search">

    <?php $form = ActiveForm::begin([
        'action' => ['index'],
        'method' => 'get',
    ]); ?>

    <?= $form->field($model, 'dataIni')->widget(DateControl::classname(), [
    	'type'=>DateControl::FORMAT_DATE,
   	 	'ajaxConversion'=>false,
    	'options' => [
        	'pluginOptions' => [
            	'autoclose' => true
       		 ]
    	],
	]) ?>
	
	<?= $form->field($model, 'dataFim')->widget(DateControl::classname(), [
    	'type'=>DateControl::FORMAT_DATE,
   	 	'ajaxConversion'=>false,
    	'options' => [
        	'pluginOptions' => [
            	'autoclose' => true
       		 ]
    	],
	]) ?>
	
    <?php //echo $form->field($model, 'profile_id') ?>

    <?php // echo $form->field($model, 'passagem_id') ?>

    <?php // echo $form->field($model, 'venda_status_id') ?>

    <div class="form-group">
        <?= Html::submitButton('Pesquisar', ['class' => 'btn btn-primary']) ?>
        <?= Html::resetButton('Limpar', ['class' => 'btn btn-default']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
