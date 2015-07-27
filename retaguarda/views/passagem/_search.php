<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;

/* @var $this yii\web\View */
/* @var $model app\models\PassagemSearch */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="passagem-search">

    <?php $form = ActiveForm::begin([
        'action' => ['index'],
        'method' => 'get',
    ]); ?>

    <?= $form->field($model, 'id') ?>

    <?= $form->field($model, 'viagem_id') ?>

    <?= $form->field($model, 'quantidade') ?>

    <?= $form->field($model, 'passagem_tipo_id') ?>

    <?= $form->field($model, 'valor') ?>

    <?php // echo $form->field($model, 'valor_desconto') ?>

    <?php // echo $form->field($model, 'data_desconto_ini') ?>

    <?php // echo $form->field($model, 'data_desconto_fim') ?>

    <div class="form-group">
        <?= Html::submitButton('Search', ['class' => 'btn btn-primary']) ?>
        <?= Html::resetButton('Reset', ['class' => 'btn btn-default']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
