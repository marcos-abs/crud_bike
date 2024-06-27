package br.ueg.progweb1.crud_bike.service;

import br.ueg.progweb1.crud_bike.repository.BikeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AppStartupRunner implements ApplicationRunner {
    public static final String NONE = "none";
    public static final String CREATE_DROP = "create-drop";
    public static final String CREATE = "create";
    public static final String UPDATE = "update";

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    private static final Logger LOG = LoggerFactory.getLogger(AppStartupRunner.class);

    @Autowired
    private BikeRepository bikeRepository;

    public void initDados() {

        LOG.info("Iniciando a execução do método initDados()");
        if(!ddlAuto.equals(CREATE) && !ddlAuto.equals(CREATE_DROP)) {
            return;
        }
        br.ueg.progweb1.crud_bike.model.Bike bike = null;
        bike = br.ueg.progweb1.crud_bike.model.Bike.builder()
                .partNumber("1234567890AB")
                .description("Bicicleta MTB, Marca Giant, 29 marchas, cor preta, freio a disco, suspensão dianteira")
                .sizeFrame(17.5)
                .sizeWheel(27.5)
                .isMTB(true)
                .createdDate(LocalDate.now().minusDays(1))
                .manufacturedDate(LocalDate.now().minusDays(180))
                .build();
        this.bikeRepository.save(bike);
        bike = br.ueg.progweb1.crud_bike.model.Bike.builder()
                .partNumber("9876543210ZY")
                .description("Bicicleta Urbana, Marca Caloi, 18 marchas, cor branca, freio v-brake, sem suspensão")
                .sizeFrame(18.0)
                .sizeWheel(29.0)
                .isMTB(true)
                .createdDate(LocalDate.now().minusDays(1))
                .manufacturedDate(LocalDate.now().minusDays(180))
                .build();
        this.bikeRepository.save(bike);
        LOG.info("Finalizando a execução do método initDados()");
    }

    public void run(ApplicationArguments args) throws Exception {
        try {
                this.initDados();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
