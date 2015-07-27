<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;

/* @var $this yii\web\View */
/* @var $model app\models\VendaSearch */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="venda-search">

    <?php $form = ActiveForm::begin([
        'action' => ['index'],
        'method' => 'get',
    ]); ?>

    <?= $form->field($model, 'id') ?>

    <?= $form->field($model, 'data') ?>

    <?= $form->field($model, 'valor') ?>

    <?= $form->field($model, 'cartao_numero') ?>

    <?= $form->field($model, 'validade') ?>

    <?php // echo $form->field($model, 'profile_id') ?>

    <?php // echo $form->field($model, 'passagem_id') ?>

    <?php // echo $form->field($model, 'venda_status_id') ?>

    <div class="form-group">
        <?= Html::submitButton('Search', ['class' => 'btn btn-primary']) ?>
        <?= Html::resetButton('Reset', ['class' => 'btn btn-default']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
