<?php

use yii\helpers\Html;
use yii\grid\GridView;

/* @var $this yii\web\View */
/* @var $searchModel app\models\PassagemSearch */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'Passagems';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="passagem-index">

    <h1><?= Html::encode($this->title) ?></h1>
    <?php // echo $this->render('_search', ['model' => $searchModel]); ?>

    <p>
        <?= Html::a('Criar Passagem', ['create'], ['class' => 'btn btn-success']) ?>
    </p>

    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'filterModel' => $searchModel,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            'id',
            'viagem_id',
            'quantidade',
            'passagem_tipo_id',
            'valor',
            // 'valor_desconto',
            // 'data_desconto_ini',
            // 'data_desconto_fim',

            ['class' => 'yii\grid\ActionColumn'],
        ],
    ]); ?>

</div>
