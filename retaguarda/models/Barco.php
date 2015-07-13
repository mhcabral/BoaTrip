<?php

namespace app\models;

use Yii;
use yii\web\UploadedFile;

/**
 * This is the model class for table "barco".
 *
 * @property string $id
 * @property string $nome
 * @property string $descricao
 * @property integer $tripulantes
 * @property string $empresa_id
 *
 * @property Empresa $empresa
 * @property BarcoFoto[] $barcoFotos
 * @property Viagem[] $viagems
 */
class Barco extends \yii\db\ActiveRecord
{
	/**
     * @var UploadedFile[]
     */
    public $imageFile;
	
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'barco';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['nome', 'descricao', 'tripulantes', 'empresa_id'], 'required'],
            [['tripulantes', 'empresa_id'], 'integer'],
            [['nome'], 'string', 'max' => 50],
            [['descricao'], 'string', 'max' => 255],
        	[['imageFile'], 'file', 'skipOnEmpty' => true, 'extensions' => 'png, jpg'],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'nome' => 'Nome',
            'descricao' => 'Descricao',
            'tripulantes' => 'Tripulantes',
            'empresa_id' => 'Empresa ID',
        	'imageFile' => 'Imagens/Fotos',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getEmpresa()
    {
        return $this->hasOne(Empresa::className(), ['id' => 'empresa_id']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getBarcoFotos()
    {
        return $this->hasMany(BarcoFoto::className(), ['barco_id' => 'id']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getViagems()
    {
        return $this->hasMany(Viagem::className(), ['barco_id' => 'id']);
    }
    
	public function upload()
    {
    	if (!($this->imageFile))
    		return false;
        if ($this->validate()) {
            $this->imageFile->saveAs('./uploads/' . $this->imageFile->baseName . '.' . $this->imageFile->extension);
            if ($this->imageFile->baseName !== '') {
            	$foto = new BarcoFoto();
            	$foto->barco_id = $this->id;
            	$foto->foto_url = './uploads/' . $this->imageFile->baseName . '.' . $this->imageFile->extension;
            	$foto->insert();
            }
            return true;
        } else {
            return false;
        }
    }
    
    public function deleteImage($foto) {
    	//$image = Yii::$app->basePath . '/uploads/' . $this->image_file;
    	
    	foreach($this->barcoFotos as $barcof) {
    		if ($barcof->id === $foto) {
    			$image = $barcof->foto_url;
    			if (unlink($image)) {
    				$barcof->delete();
    				return true;
    			}
    		}
    	}
    	return false;
    }
}
