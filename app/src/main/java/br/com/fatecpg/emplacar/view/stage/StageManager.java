package br.com.fatecpg.emplacar.view.stage;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.fatecpg.emplacar.R;
import br.com.fatecpg.emplacar.cards.CardType;
import br.com.fatecpg.emplacar.domain.builder.ExamDataBuilder;
import br.com.fatecpg.emplacar.domain.builder.QuestionBuilder;
import br.com.fatecpg.emplacar.domain.builder.RewardBuilder;
import br.com.fatecpg.emplacar.domain.entity.Exam;
import br.com.fatecpg.emplacar.domain.entity.Reward;
import br.com.fatecpg.emplacar.domain.vo.Alternative;
import br.com.fatecpg.emplacar.domain.vo.ExamData;
import br.com.fatecpg.emplacar.view.activities.levels.ChooseVehicleActivity;
import br.com.fatecpg.emplacar.view.activities.levels.OverrunConditionsActivity;
import br.com.fatecpg.emplacar.view.activities.levels.TrafficViolationActivity;
import br.com.fatecpg.emplacar.view.activities.levels.ViasMapsActivity;
import br.com.fatecpg.emplacar.view.utils.ReflectionUtils;

public class StageManager {
    private static final String TAG = "StageManager";
    private ArrayList<Stage> allStages = new ArrayList<>();

    public StageManager() {
        allStages.addAll(fillStages());
    }


    public Intent getNext(Context mContext, StageHolder stageHolder) {
        int nextIndex = stageHolder.getStageCount();
        Intent i = null;
        try {
            Stage stage = allStages.get(nextIndex);
            i = createIntent(ReflectionUtils.getClass(stage.getClassName()), mContext);
        } catch (ClassNotFoundException e) {
            Log.e(TAG, e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            Log.e(TAG, "Course completed");
            return null;
        }
        return i;
    }

    public void updateRewards(int index) {
        if (index < 0)
            return;

        Stage stage = allStages.get(index);
        for (Reward reward : stage.getRewardList()) {
            reward.setNew(true);
            reward.save();
        }


        ExamData examData = stage.getExamData();

        if (examData != null) {
            Exam exam = new Exam(examData);
            exam.setNew(true);
            exam.save();
        }
    }

    private Intent createIntent(Class next, Context mContext) {
        return new Intent(mContext, next);
    }

    public List<Stage> fillStages() {
        Stage chooseVehicle = StageBuilder.aStage()
                .className(ChooseVehicleActivity.class.getName())
                .rewardList(Arrays.asList(
                        RewardBuilder.aReward()
                                .imgResource(R.drawable.placa_1_r_16)
                                .cardType(CardType.SIGN)
                                .build(),
                        RewardBuilder.aReward()
                                .imgResource(R.drawable.placa_3_a_11b)
                                .cardType(CardType.SIGN)
                                .build(),
                        RewardBuilder.aReward()
                                .imgResource(R.drawable.placa_5)
                                .cardType(CardType.SIGN)
                                .build()
                ))
                .build();


        Stage overrunConditions = StageBuilder.aStage()
                .className(OverrunConditionsActivity.class.getName())
                .rewardList(Arrays.asList(
                        RewardBuilder
                                .aReward()
                                .imgResource(R.drawable.placa_7)
                                .cardType(CardType.SIGN)
                                .build(),
                        RewardBuilder
                                .aReward()
                                .imgResource(R.drawable.placa_8)
                                .cardType(CardType.SIGN)
                                .build(),
                        RewardBuilder
                                .aReward()
                                .imgResource(R.drawable.placa_10)
                                .cardType(CardType.SIGN)
                                .build()
                ))
                .examData(
                        ExamDataBuilder
                                .anExamData()
                                .thumb(R.drawable.ctb_exam)
                                .title("Legislação - Básico")
                                .questions(Arrays.asList(
                                        QuestionBuilder
                                                .aQuestion()
                                                .text("Qual a categoria da CNH utilizada para dirigir veículos de passageiros com capacidade acima de oito lugares sem contar o condutor")
                                                .alternatives(Arrays.asList(
                                                        new Alternative("A", false),
                                                        new Alternative("B", false),
                                                        new Alternative("C", false),
                                                        new Alternative("D", true))).build(),
                                        QuestionBuilder
                                                .aQuestion()
                                                .text("Quando no trânsito existe uma placa de regulamentação permitindo o estacionamento, você entende que é permitido estacionar:")
                                                .alternatives(Arrays.asList(
                                                        new Alternative("Dentro do espaço permitido em até cinqüenta centímetros distante do passeio.", true),
                                                        new Alternative("A dez metros da placa.", false),
                                                        new Alternative("A mais de oito metros do alinhamento da via transversal.", false),
                                                        new Alternative("Antes da placa.", false))).build(),
                                        QuestionBuilder
                                                .aQuestion()
                                                .text("Qual a velocidade que um ônibus pode desenvolver em uma rodovia sem sinalização?")
                                                .alternatives(Arrays.asList(
                                                        new Alternative("70kmh", false),
                                                        new Alternative("80kmh.", false),
                                                        new Alternative("90kmh", true),
                                                        new Alternative("100kmh", false))).build(),
                                        QuestionBuilder
                                                .aQuestion()
                                                .text("Qual a idade que uma pessoa deve ter para se habilitar nas categorias (D) e (E)?")
                                                .alternatives(Arrays.asList(
                                                        new Alternative("17 anos", false),
                                                        new Alternative("19 anos", false),
                                                        new Alternative("21 anos", true),
                                                        new Alternative("18 anos", false))).build()))
                                .build()).build();

        Stage trafficViolation = StageBuilder.aStage()
                .className(TrafficViolationActivity.class.getName())
                .rewardList(Arrays.asList(
                        RewardBuilder.aReward()
                                .imgResource(R.drawable.multa_1_trafegar_na_calcada)
                                .cardType(CardType.TRAFFIC_TICKET)
                                .build(),
                        RewardBuilder.aReward()
                                .imgResource(R.drawable.multa_6_parado_falta_combustivel)
                                .cardType(CardType.TRAFFIC_TICKET)
                                .build(),
                        RewardBuilder.aReward()
                                .imgResource(R.drawable.multa_16_dirigir_utilizando_celular)
                                .cardType(CardType.TRAFFIC_TICKET)
                                .build(),
                        RewardBuilder.aReward()
                                .imgResource(R.drawable.multa_2_dirigir_sem_cnh)
                                .cardType(CardType.TRAFFIC_TICKET)
                                .build(),
                        RewardBuilder.aReward()
                                .imgResource(R.drawable.multa_4_dirigir_sem_cinto)
                                .cardType(CardType.TRAFFIC_TICKET)
                                .build()
                )).examData(
                        ExamDataBuilder
                                .anExamData()
                                .thumb(R.drawable.violation_exam)
                                .title("Legislação - Penalidades")
                                .questions(Arrays.asList(
                                        QuestionBuilder
                                                .aQuestion()
                                                .text("Uma infração média corresponde ao seguinte número de pontos:")
                                                .alternatives(Arrays.asList(
                                                        new Alternative("4", true),
                                                        new Alternative("7", false),
                                                        new Alternative("3", false),
                                                        new Alternative("5", false))).build(),
                                        QuestionBuilder
                                                .aQuestion()
                                                .text("Quando o condutor desobedece a uma placa de regulamentação no trânsito, estará cometendo? ")
                                                .alternatives(Arrays.asList(
                                                        new Alternative("Multa", false),
                                                        new Alternative("Infração de trânsito", true),
                                                        new Alternative("Notificação", false),
                                                        new Alternative("Penalidade.", false))).build(),
                                        QuestionBuilder
                                                .aQuestion()
                                                .text("Se um veículo ao transitar nas vias públicas produzir ruídos excedentes, devido a instalação de silenciadores inadequados, estará sujeito a : ")
                                                .alternatives(Arrays.asList(
                                                        new Alternative("Multa.", false),
                                                        new Alternative("Multa e retenção do veículo", false),
                                                        new Alternative("Multa e remoção do veículo. ", true),
                                                        new Alternative("Multa e suspensão do direto de dirigir.", false))).build()))
                                .build())
                .build();

        Stage vias = StageBuilder.aStage().className(ViasMapsActivity.class.getName())
                .rewardList(Arrays.asList(
                        RewardBuilder.aReward()
                                .imgResource(R.drawable.multa_3_dirigir_sob_influencia_alcool_entorpecentes)
                                .cardType(CardType.TRAFFIC_TICKET)
                                .build(),
                        RewardBuilder.aReward()
                                .imgResource(R.drawable.multa_17_velocidade_maxima_50)
                                .cardType(CardType.TRAFFIC_TICKET)
                                .build(),
                        RewardBuilder.aReward()
                                .imgResource(R.drawable.multa_15_dirigir_sem_placa)
                                .cardType(CardType.TRAFFIC_TICKET)
                                .build(),
                        RewardBuilder.aReward()
                                .imgResource(R.drawable.placa_19)
                                .cardType(CardType.SIGN)
                                .build(),
                        RewardBuilder.aReward()
                                .imgResource(R.drawable.placa_13)
                                .cardType(CardType.SIGN)
                                .build()
                )).build();
        return Arrays.asList(chooseVehicle, overrunConditions, trafficViolation, vias);
    }
}
