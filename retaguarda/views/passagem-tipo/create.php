<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\PassagemTipo */

$this->title = 'Criar Passagem Tipo';
$this->params['breadcrumbs'][] = ['label' => 'Passagem Tipos', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="passagem-tipo-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
