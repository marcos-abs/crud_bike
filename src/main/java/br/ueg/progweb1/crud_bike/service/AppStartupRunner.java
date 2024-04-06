package br.ueg.progweb1.aula01.service;

import br.ueg.progweb1.aula01.model.Student;
import br.ueg.progweb1.aula01.repository.StudentRepository;
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
    private StudentRepository studentRepository;

    public void initDados() {

        LOG.info("Iniciando a execução do método initDados()");
        if(!ddlAuto.equals(CREATE) && !ddlAuto.equals(CREATE_DROP)) {
            return;
        }
        Student student = Student.builder()
                .registerNumber("230322001")
                .name("João das Couves")
                .course("Ciência da Computação")
                .createdDate(LocalDate.now().minusDays(1))
                .build();
        this.studentRepository.save(student);
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
