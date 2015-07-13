<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;

/* @var $this yii\web\View */
/* @var $model app\models\ViagemSearch */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="viagem-search">

    <?php $form = ActiveForm::begin([
        'action' => ['index'],
        'method' => 'get',
    ]); ?>

    <?= $form->field($model, 'id') ?>

    <?= $form->field($model, 'data_saida') ?>

    <?= $form->field($model, 'data_chegada') ?>

    <?= $form->field($model, 'valor') ?>

    <?= $form->field($model, 'valor_desconto') ?>

    <?php // echo $form->field($model, 'data_desconto_ini') ?>

    <?php // echo $form->field($model, 'data_desconto_fim') ?>

    <?php // echo $form->field($model, 'percurso') ?>

    <?php // echo $form->field($model, 'barco_id') ?>

    <?php // echo $form->field($model, 'localidade_origem') ?>

    <?php // echo $form->field($model, 'localidade_destino') ?>

    <div class="form-group">
        <?= Html::submitButton('Search', ['class' => 'btn btn-primary']) ?>
        <?= Html::resetButton('Reset', ['class' => 'btn btn-default']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
