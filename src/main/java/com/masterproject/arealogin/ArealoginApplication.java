package com.masterproject.arealogin;

import com.masterproject.arealogin.model.Aula;
import com.masterproject.arealogin.model.Curso;
import com.masterproject.arealogin.model.Usuario;
import com.masterproject.arealogin.repository.AulaRepository;
import com.masterproject.arealogin.repository.CursoRepository;
import com.masterproject.arealogin.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ArealoginApplication {

        public static void main(String[] args) {
                SpringApplication.run(ArealoginApplication.class, args);
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public CommandLineRunner initData(
                        UsuarioRepository usuarioRepository,
                        CursoRepository cursoRepository,
                        AulaRepository aulaRepository,
                        PasswordEncoder passwordEncoder) {
                return args -> {

                        // --- 1. CRIAÇÃO DE TODOS OS 11 CURSOS ---
                        if (cursoRepository.count() == 0) {
                                System.out.println("Criando cursos padrão...");

                                Curso pbi = new Curso();
                                pbi.setTitulo("Power BI");
                                pbi.setDescricao(
                                                "Domine a ferramenta de Business Intelligence mais poderosa do mercado.");

                                Curso excel = new Curso();
                                excel.setTitulo("Excel Pro");
                                excel.setDescricao("Do básico ao avançado, torne-se um mestre em planilhas.");

                                Curso gestao = new Curso();
                                gestao.setTitulo("Gestão de Projetos Híbrida");
                                gestao.setDescricao(
                                                "Aprenda a gerenciar projetos com metodologias ágeis e tradicionais.");
                                gestao.setSlug("gestao-de-projetos-hibrida"); // <-- DEFINIR O SLUG
                                cursoRepository.save(gestao);

                                Curso trilha = new Curso();
                                trilha.setTitulo("Trilha Master");
                                trilha.setDescricao("Uma jornada completa para se tornar um especialista em dados.");

                                Curso pmi1 = new Curso();
                                pmi1.setTitulo("Gerenciamento de Projetos com Ênfase no PMI - Da Iniciação ao Planejamento");
                                pmi1.setDescricao(
                                                "Aprenda os fundamentos essenciais do PMI para iniciar e planejar projetos de sucesso.");

                                Curso pmi2 = new Curso();
                                pmi2.setTitulo("Gerenciamento de Projetos com Ênfase no PMI - Planejamento Avançado");
                                pmi2.setDescricao(
                                                "Aprofunde-se nas técnicas de planejamento de escopo, tempo e custos.");

                                Curso pmi3 = new Curso();
                                pmi3.setTitulo("Gerenciamento de Projetos com Ênfase no PMI - Execução, Monitoramento e Controle");
                                pmi3.setDescricao(
                                                "Domine a execução e o controle de projetos para garantir a entrega de resultados.");

                                Curso bpm = new Curso();
                                bpm.setTitulo("Análise de Negócio - BPM");
                                bpm.setDescricao(
                                                "Entenda e modele processos de negócio para otimizar a performance da sua empresa.");

                                Curso bizagi = new Curso();
                                bizagi.setTitulo("BPMN com Bizagi");
                                bizagi.setDescricao(
                                                "Aprenda na prática a modelar processos usando a notação BPMN com a ferramenta Bizagi.");

                                Curso jira = new Curso();
                                jira.setTitulo("Jira Software - Gestão Ágil de Projetos e Operações");
                                jira.setDescricao(
                                                "Gerencie seus projetos ágeis de forma eficiente com a ferramenta líder de mercado.");

                                Curso okr = new Curso();
                                okr.setTitulo("Gestão de Projetos com OKR");
                                okr.setDescricao(
                                                "Alinhe os objetivos da sua equipe com os resultados estratégicos da empresa usando OKRs.");

                                cursoRepository.saveAll(List.of(pbi, excel, gestao, trilha, pmi1, pmi2, pmi3, bpm,
                                                bizagi, jira, okr));
                                System.out.println("11 cursos padrão criados com sucesso!");
                        }

                        // --- 2. CRIAÇÃO DAS AULAS REAIS ---
                        if (aulaRepository.count() == 0 && cursoRepository.count() > 0) {
                                System.out.println("Criando aulas de exemplo...");
                                List<Aula> todasAsAulas = new ArrayList<>();

                                // --- AULAS PARA O CURSO DE GESTÃO DE PROJETOS HÍBRIDA ---
                                Curso cursoGestao = cursoRepository.findByTitulo("Gestão de Projetos Híbrida").get();

                                String videoUrlBase = "https://masterproject-videos-cursos.s3.us-east-2.amazonaws.com/";

                                Aula gestaoAula1 = new Aula();
                                gestaoAula1.setTitulo("Aula 1.1:");
                                gestaoAula1.setSlug("Aula1.1");
                                gestaoAula1.setConteudo(
                                                "Nesta aula introdutória, exploramos os conceitos fundamentais da gestão híbrida de projetos, combinando metodologias tradicionais e ágeis.");
                                gestaoAula1.setVideoUrl(videoUrlBase + "Aula 1.1 - Treinamento - Master Project - Gestão de Projetos Híbrida-20250315_151707-Meeting Recording.mp4");
                                                gestaoAula1.setGratuita(true);
                                gestaoAula1.setCurso(cursoGestao);

                                Aula gestaoAula2 = new Aula();
                                gestaoAula2.setTitulo("Aula 1.2:");
                                gestaoAula1.setSlug("Aula1.2");
                                gestaoAula2.setConteudo( "Analisando as diferenças entre as abordagens tradicional e ágil, e quando usar cada metodologia.");
                                gestaoAula2.setVideoUrl(videoUrlBase + "Aula 1.2 - Treinamento - Master Project - Gestão de Projetos Híbrida-20250315_191240-Meeting Recording.mp4");
                                gestaoAula2.setCurso(cursoGestao);

                                Aula gestaoAula3 = new Aula();
                                gestaoAula3.setTitulo("Aula 1.3:");
                                gestaoAula1.setSlug("Aula1.3");
                                gestaoAula3.setConteudo( "Passo a passo para implementar a gestão híbrida no seu time, com exemplos práticos.");
                                gestaoAula2.setVideoUrl(videoUrlBase + "Aula 1.3 - Treinamento - Master Project - Gestão de Projetos Híbrida-20250315_210112-Meeting Recording.mp4");
                                gestaoAula3.setCurso(cursoGestao);
                                
                                Aula gestaoAula4 = new Aula();
                                gestaoAula4.setTitulo("Aula 2.1:");
                                gestaoAula1.setSlug("Aula2.1");
                                gestaoAula4.setConteudo("Conheça as ferramentas que facilitam o gerenciamento híbrido de projetos.");
                                gestaoAula2.setVideoUrl(videoUrlBase + "Aula 2.1 - Treinamento - Master Project - Gestão de Projetos Híbrida-20250322_150647-Meeting Recording.mp4");
                                gestaoAula4.setCurso(cursoGestao);

                                Aula gestaoAula5 = new Aula();
                                gestaoAula5.setTitulo("Aula 2.2:");
                                gestaoAula1.setSlug("Aula2.2");
                                gestaoAula5.setConteudo("Como medir o sucesso de um projeto gerenciado de forma híbrida, com KPIs específicos.");
                                gestaoAula2.setVideoUrl(videoUrlBase + "Aula 2.2 - Treinamento - Master Project - Gestão de Projetos Híbrida-20250322_190640-Meeting Recording.mp4");
                                gestaoAula5.setCurso(cursoGestao);

                                Aula gestaoAula6 = new Aula();
                                gestaoAula6.setTitulo("Aula 2.3:");
                                gestaoAula1.setSlug("Aula2.3");
                                gestaoAula6.setConteudo( "Analisando um projeto real que utilizou a abordagem híbrida com sucesso.");
                                gestaoAula2.setVideoUrl(videoUrlBase + "Aula 2.3 - Treinamento - Master Project - Gestão de Projetos Híbrida-20250322_210211-Meeting Recording.mp4");
                                gestaoAula6.setCurso(cursoGestao);

                                Aula gestaoAula77 = new Aula();
                                gestaoAula77.setTitulo("Aula 3.1:");
                                gestaoAula1.setSlug("Aula3.1");
                                gestaoAula77.setConteudo("Como definir escopo e cronograma em um projeto.");
                                gestaoAula2.setVideoUrl(videoUrlBase + "Aula 3.1 - Treinamento - Master Project - Gestão de Projetos Híbrida-20250329_152149-Meeting Recording.mp4");
                                gestaoAula77.setCurso(cursoGestao);

                                Aula gestaoAula7 = new Aula();
                                gestaoAula7.setTitulo("Aula 3.2:");
                                gestaoAula1.setSlug("Aula3.2");
                                gestaoAula7.setConteudo("Como definir escopo e cronograma em um projeto.");
                                gestaoAula2.setVideoUrl(videoUrlBase + "Aula 3.2 - Treinamento - Master Project - Gestão de Projetos Híbrida-20250329_181608-Meeting Recording.mp4");
                                gestaoAula7.setCurso(cursoGestao);

                                Aula gestaoAula8 = new Aula();
                                gestaoAula8.setTitulo("Aula 3.3:");
                                gestaoAula1.setSlug("Aula3.3");
                                gestaoAula8.setConteudo("Planejamento e controle de custos e garantia de qualidade.");
                                gestaoAula2.setVideoUrl(videoUrlBase + "Aula 3.3 - Treinamento - Master Project - Gestão de Projetos Híbrida-20250329_210307-Meeting Recording.mp4");
                                gestaoAula8.setCurso(cursoGestao);

                                // Aulas Módulo 4
                                Aula gestaoAula9 = new Aula();
                                gestaoAula9.setTitulo("Aula 4.1:");
                                gestaoAula1.setSlug("Aula4.1");
                                gestaoAula9.setConteudo("Formação de equipes de alto desempenho.");
                                gestaoAula2.setVideoUrl(videoUrlBase + "Aula 4.1 - Treinamento - Master Project - Gestão de Projetos Híbrida-20250405_152000-Meeting Recording.mp4");
                                gestaoAula9.setCurso(cursoGestao);

                                Aula gestaoAula10 = new Aula();
                                gestaoAula10.setTitulo("Aula 4.2:");
                                gestaoAula1.setSlug("Aula4.2");
                                gestaoAula10.setConteudo( "Ferramentas e técnicas para melhorar a comunicação no projeto.");
                                gestaoAula2.setVideoUrl(videoUrlBase + "Aula 4.2 - Treinamento - Master Project - Gestão de Projetos Híbrida-20250405_192001-Meeting Recording.mp4");
                                gestaoAula10.setCurso(cursoGestao);

                                Aula gestaoAula11 = new Aula();
                                gestaoAula11.setTitulo("Aula 4.3:");
                                gestaoAula1.setSlug("Aula4.3");
                                gestaoAula11.setConteudo("Identificação, análise e plano de resposta a riscos.");
                                gestaoAula2.setVideoUrl(videoUrlBase + "Aula 4.3 - Treinamento - Master Project - Gestão de Projetos Híbrida-20250405_210645-Meeting Recording.mp4");
                                gestaoAula11.setCurso(cursoGestao);

                                // Aulas Módulo 5
                                Aula gestaoAula12 = new Aula();
                                gestaoAula12.setTitulo("Aula 5.1:");
                                gestaoAula1.setSlug("Aula5.1");
                                gestaoAula12.setConteudo( "Mapeamento e engajamento das partes interessadas do projeto.");
                                gestaoAula2.setVideoUrl(videoUrlBase + "Aula 5.1 - Treinamento - Master Project - Gestão de Projetos Híbrida-20250412_150238-Meeting Recording.mp4");
                                gestaoAula12.setCurso(cursoGestao);

                                Aula gestaoAula13 = new Aula();
                                gestaoAula13.setTitulo("Aula 5.2:");
                                gestaoAula1.setSlug("Aula5.2");
                                gestaoAula13.setConteudo("Uso de softwares e metodologias de apoio.");
                                gestaoAula13.setVideoUrl("Aula 5.2 - Treinamento - Master Project - Gestão de Projetos Híbrida-20250412_180826-Meeting Recording.mp4");
                                gestaoAula13.setCurso(cursoGestao);

                                Aula gestaoAula14 = new Aula();
                                gestaoAula14.setTitulo("Aula 5.3:");
                                gestaoAula1.setSlug("Aula5.3");
                                gestaoAula14.setConteudo("Processos de finalização, lições aprendidas e documentação.");
                                gestaoAula14.setVideoUrl("Aula 5.3 - Treinamento - Master Project - Gestão de Projetos Híbrida-20250412_211222-Meeting Recording.mp4");
                                gestaoAula14.setCurso(cursoGestao);

                                todasAsAulas
                                                .addAll(List.of(gestaoAula1, gestaoAula2, gestaoAula3, gestaoAula4,
                                                                gestaoAula5, gestaoAula6, gestaoAula7, gestaoAula77,
                                                                gestaoAula8,
                                                                gestaoAula9, gestaoAula10, gestaoAula11, gestaoAula12,
                                                                gestaoAula13, gestaoAula14));

                                // --- AULAS PARA O CURSO DE POWER BI ---
                                Curso cursoPbi = cursoRepository.findByTitulo("Power BI").get();

                                Aula pbiAula1 = new Aula();
                                pbiAula1.setTitulo("Aula 1: Introdução ao Power BI");
                                pbiAula1.setConteudo("Conhecendo a interface e os principais componentes do Power BI.");
                                pbiAula1.setVideoUrl("https://www.youtube.com/embed/SEU_OUTRO_VIDEO_AQUI");
                                pbiAula1.setGratuita(true);
                                pbiAula1.setCurso(cursoPbi);

                                Aula pbiAula2 = new Aula();
                                pbiAula2.setTitulo("Aula 2: Conectando e Transformando Dados");
                                pbiAula2.setConteudo(
                                                "Como importar dados de diversas fontes e usar o Power Query para transformações.");
                                pbiAula2.setVideoUrl("https://www.youtube.com/embed/SEU_OUTRO_VIDEO_AQUI");
                                pbiAula2.setCurso(cursoPbi);

                                Aula pbiAula3 = new Aula();
                                pbiAula3.setTitulo("Aula 3: Modelagem de Dados");
                                pbiAula3.setConteudo(
                                                "Criando relacionamentos entre tabelas e modelos de dados eficientes.");
                                pbiAula3.setVideoUrl("https://www.youtube.com/embed/SEU_OUTRO_VIDEO_AQUI");
                                pbiAula3.setCurso(cursoPbi);

                                Aula pbiAula4 = new Aula();
                                pbiAula4.setTitulo("Aula 4: DAX - Fundamentos");
                                pbiAula4.setConteudo(
                                                "Introdução à linguagem DAX para criação de medidas e colunas calculadas.");
                                pbiAula4.setVideoUrl("https://www.youtube.com/embed/SEU_OUTRO_VIDEO_AQUI");
                                pbiAula4.setCurso(cursoPbi);

                                Aula pbiAula5 = new Aula();
                                pbiAula5.setTitulo("Aula 5: Visualizações Avançadas");
                                pbiAula5.setConteudo(
                                                "Criando visualizações interativas e personalizadas para seus dashboards.");
                                pbiAula5.setVideoUrl("https://www.youtube.com/embed/SEU_OUTRO_VIDEO_AQUI");
                                pbiAula5.setCurso(cursoPbi);

                                Aula pbiAula6 = new Aula();
                                pbiAula6.setTitulo("Aula 6: Publicação e Compartilhamento");
                                pbiAula6.setConteudo(
                                                "Como publicar relatórios no Power BI Service e compartilhar com stakeholders.");
                                pbiAula6.setVideoUrl("https://www.youtube.com/embed/SEU_OUTRO_VIDEO_AQUI");
                                pbiAula6.setCurso(cursoPbi);

                                todasAsAulas.addAll(
                                                List.of(pbiAula1, pbiAula2, pbiAula3, pbiAula4, pbiAula5, pbiAula6));

                                // --- AULAS PARA O CURSO DE EXCEL PRO ---
                                Curso cursoExcel = cursoRepository.findByTitulo("Excel Pro").get();

                                Aula excelAula1 = new Aula();
                                excelAula1.setTitulo("Aula 1: Interface e Fundamentos");
                                excelAula1.setConteudo(
                                                "Conhecendo a interface do Excel e conceitos básicos de planilhas.");
                                excelAula1.setVideoUrl("https://www.youtube.com/embed/EXCEL_VIDEO_AQUI");
                                excelAula1.setGratuita(true);
                                excelAula1.setCurso(cursoExcel);

                                Aula excelAula2 = new Aula();
                                excelAula2.setTitulo("Aula 2: Fórmulas Essenciais");
                                excelAula2.setConteudo(
                                                "Domine as principais funções do Excel: SOMA, SE, PROCV, CONT.SE, etc.");
                                excelAula2.setVideoUrl("https://www.youtube.com/embed/EXCEL_VIDEO_AQUI");
                                excelAula2.setCurso(cursoExcel);

                                Aula excelAula3 = new Aula();
                                excelAula3.setTitulo("Aula 3: Tabelas e Formatação Condicional");
                                excelAula3.setConteudo(
                                                "Trabalhando com tabelas dinâmicas e formatação condicional avançada.");
                                excelAula3.setVideoUrl("https://www.youtube.com/embed/EXCEL_VIDEO_AQUI");
                                excelAula3.setCurso(cursoExcel);

                                Aula excelAula4 = new Aula();
                                excelAula4.setTitulo("Aula 4: Gráficos e Dashboard");
                                excelAula4.setConteudo("Criando visualizações impactantes e dashboards interativos.");
                                excelAula4.setVideoUrl("https://www.youtube.com/embed/EXCEL_VIDEO_AQUI");
                                excelAula4.setCurso(cursoExcel);

                                Aula excelAula5 = new Aula();
                                excelAula5.setTitulo("Aula 5: Power Query e Power Pivot");
                                excelAula5.setConteudo("Introdução às ferramentas de business intelligence do Excel.");
                                excelAula5.setVideoUrl("https://www.youtube.com/embed/EXCEL_VIDEO_AQUI");
                                excelAula5.setCurso(cursoExcel);

                                Aula excelAula6 = new Aula();
                                excelAula6.setTitulo("Aula 6: Macros e Automação");
                                excelAula6.setConteudo(
                                                "Gravação e edição de macros para automatizar tarefas repetitivas.");
                                excelAula6.setVideoUrl("https://www.youtube.com/embed/EXCEL_VIDEO_AQUI");
                                excelAula6.setCurso(cursoExcel);

                                todasAsAulas.addAll(List.of(excelAula1, excelAula2, excelAula3, excelAula4, excelAula5,
                                                excelAula6));

                                // --- AULAS PARA O CURSO PMI 1 - INICIAÇÃO AO PLANEJAMENTO ---
                                Curso cursoPmi1 = cursoRepository
                                                .findByTitulo("Gerenciamento de Projetos com Ênfase no PMI - Da Iniciação ao Planejamento")
                                                .get();

                                Aula pmi1Aula1 = new Aula();
                                pmi1Aula1.setTitulo("Aula 1: Fundamentos do PMI e Guia PMBOK");
                                pmi1Aula1.setConteudo("Introdução ao PMI, certificações e estrutura do Guia PMBOK.");
                                pmi1Aula1.setVideoUrl("https://www.youtube.com/embed/PMI_VIDEO_AQUI");
                                pmi1Aula1.setGratuita(true);
                                pmi1Aula1.setCurso(cursoPmi1);

                                Aula pmi1Aula2 = new Aula();
                                pmi1Aula2.setTitulo("Aula 2: Iniciação de Projetos");
                                pmi1Aula2.setConteudo(
                                                "Processos de iniciação: termo de abertura e registro de partes interessadas.");
                                pmi1Aula2.setVideoUrl("https://www.youtube.com/embed/PMI_VIDEO_AQUI");
                                pmi1Aula2.setCurso(cursoPmi1);

                                Aula pmi1Aula3 = new Aula();
                                pmi1Aula3.setTitulo("Aula 3: Planejamento de Escopo");
                                pmi1Aula3.setConteudo(
                                                "Coleta de requisitos, EAP (Estrutura Analítica do Projeto) e definição de escopo.");
                                pmi1Aula3.setVideoUrl("https://www.youtube.com/embed/PMI_VIDEO_AQUI");
                                pmi1Aula3.setCurso(cursoPmi1);

                                Aula pmi1Aula4 = new Aula();
                                pmi1Aula4.setTitulo("Aula 4: Planejamento de Cronograma");
                                pmi1Aula4.setConteudo(
                                                "Definição e sequenciamento de atividades, estimativas de duração e desenvolvimento do cronograma.");
                                pmi1Aula4.setVideoUrl("https://www.youtube.com/embed/PMI_VIDEO_AQUI");
                                pmi1Aula4.setCurso(cursoPmi1);

                                Aula pmi1Aula5 = new Aula();
                                pmi1Aula5.setTitulo("Aula 5: Planejamento de Custos");
                                pmi1Aula5.setConteudo("Estimativa de custos, orçamentação e linha de base de custos.");
                                pmi1Aula5.setVideoUrl("https://www.youtube.com/embed/PMI_VIDEO_AQUI");
                                pmi1Aula5.setCurso(cursoPmi1);

                                Aula pmi1Aula6 = new Aula();
                                pmi1Aula6.setTitulo("Aula 6: Plano de Gerenciamento de Projeto");
                                pmi1Aula6.setConteudo(
                                                "Consolidando todos os planos auxiliares no plano de gerenciamento do projeto.");
                                pmi1Aula6.setVideoUrl("https://www.youtube.com/embed/PMI_VIDEO_AQUI");
                                pmi1Aula6.setCurso(cursoPmi1);

                                todasAsAulas.addAll(List.of(pmi1Aula1, pmi1Aula2, pmi1Aula3, pmi1Aula4, pmi1Aula5,
                                                pmi1Aula6));

                                // --- AULAS PARA O CURSO PMI 2 - PLANEJAMENTO AVANÇADO ---
                                Curso cursoPmi2 = cursoRepository
                                                .findByTitulo("Gerenciamento de Projetos com Ênfase no PMI - Planejamento Avançado")
                                                .get();

                                Aula pmi2Aula1 = new Aula();
                                pmi2Aula1.setTitulo("Aula 1: Revisão dos Fundamentos de Planejamento");
                                pmi2Aula1.setConteudo("Revisão dos conceitos essenciais de planejamento de projetos.");
                                pmi2Aula1.setVideoUrl("https://www.youtube.com/embed/PMI2_VIDEO_AQUI");
                                pmi2Aula1.setGratuita(true);
                                pmi2Aula1.setCurso(cursoPmi2);

                                Aula pmi2Aula2 = new Aula();
                                pmi2Aula2.setTitulo("Aula 2: Planejamento de Riscos");
                                pmi2Aula2.setConteudo(
                                                "Identificação, análise qualitativa e quantitativa de riscos, e planejamento de respostas.");
                                pmi2Aula2.setVideoUrl("https://www.youtube.com/embed/PMI2_VIDEO_AQUI");
                                pmi2Aula2.setCurso(cursoPmi2);

                                Aula pmi2Aula3 = new Aula();
                                pmi2Aula3.setTitulo("Aula 3: Planejamento de Qualidade");
                                pmi2Aula3.setConteudo("Gestão da qualidade: planejamento, garantia e controle.");
                                pmi2Aula3.setVideoUrl("https://www.youtube.com/embed/PMI2_VIDEO_AQUI");
                                pmi2Aula3.setCurso(cursoPmi2);

                                Aula pmi2Aula4 = new Aula();
                                pmi2Aula4.setTitulo("Aula 4: Planejamento de Recursos");
                                pmi2Aula4.setConteudo(
                                                "Planejamento de recursos humanos, físicos e materiais do projeto.");
                                pmi2Aula4.setVideoUrl("https://www.youtube.com/embed/PMI2_VIDEO_AQUI");
                                pmi2Aula4.setCurso(cursoPmi2);

                                Aula pmi2Aula5 = new Aula();
                                pmi2Aula5.setTitulo("Aula 5: Planejamento de Comunicações");
                                pmi2Aula5.setConteudo(
                                                "Estratégia de comunicação, matriz de comunicação e canal de comunicação.");
                                pmi2Aula5.setVideoUrl("https://www.youtube.com/embed/PMI2_VIDEO_AQUI");
                                pmi2Aula5.setCurso(cursoPmi2);

                                Aula pmi2Aula6 = new Aula();
                                pmi2Aula6.setTitulo("Aula 6: Planejamento de Aquisições");
                                pmi2Aula6.setConteudo("Processos de aquisição, contratação e gestão de fornecedores.");
                                pmi2Aula6.setVideoUrl("https://www.youtube.com/embed/PMI2_VIDEO_AQUI");
                                pmi2Aula6.setCurso(cursoPmi2);

                                todasAsAulas.addAll(List.of(pmi2Aula1, pmi2Aula2, pmi2Aula3, pmi2Aula4, pmi2Aula5,
                                                pmi2Aula6));

                                // --- AULAS PARA O CURSO PMI 3 - EXECUÇÃO, MONITORAMENTO E CONTROLE ---
                                Curso cursoPmi3 = cursoRepository
                                                .findByTitulo(
                                                                "Gerenciamento de Projetos com Ênfase no PMI - Execução, Monitoramento e Controle")
                                                .get();

                                Aula pmi3Aula1 = new Aula();
                                pmi3Aula1.setTitulo("Aula 1: Direcionamento e Gerenciamento do Trabalho");
                                pmi3Aula1.setConteudo(
                                                "Processos de execução: direcionar e gerenciar o trabalho do projeto.");
                                pmi3Aula1.setVideoUrl("https://www.youtube.com/embed/PMI3_VIDEO_AQUI");
                                pmi3Aula1.setGratuita(true);
                                pmi3Aula1.setCurso(cursoPmi3);

                                Aula pmi3Aula2 = new Aula();
                                pmi3Aula2.setTitulo("Aula 2: Gerenciamento do Conhecimento e Qualidade");
                                pmi3Aula2.setConteudo(
                                                "Gerenciar o conhecimento do projeto e realizar a garantia da qualidade.");
                                pmi3Aula2.setVideoUrl("https://www.youtube.com/embed/PMI3_VIDEO_AQUI");
                                pmi3Aula2.setCurso(cursoPmi3);

                                Aula pmi3Aula3 = new Aula();
                                pmi3Aula3.setTitulo("Aula 3: Aquisição e Gerenciamento de Recursos");
                                pmi3Aula3.setConteudo("Adquirir, desenvolver e gerenciar a equipe do projeto.");
                                pmi3Aula3.setVideoUrl("https://www.youtube.com/embed/PMI3_VIDEO_AQUI");
                                pmi3Aula3.setCurso(cursoPmi3);

                                Aula pmi3Aula4 = new Aula();
                                pmi3Aula4.setTitulo("Aula 4: Monitoramento e Controle do Trabalho");
                                pmi3Aula4.setConteudo(
                                                "Monitorar e controlar o trabalho do projeto e realizar controle integrado de mudanças.");
                                pmi3Aula4.setVideoUrl("https://www.youtube.com/embed/PMI3_VIDEO_AQUI");
                                pmi3Aula4.setCurso(cursoPmi3);

                                Aula pmi3Aula5 = new Aula();
                                pmi3Aula5.setTitulo("Aula 5: Controle de Escopo, Cronograma e Custos");
                                pmi3Aula5.setConteudo(
                                                "Validar e controlar o escopo, controlar o cronograma e os custos.");
                                pmi3Aula5.setVideoUrl("https://www.youtube.com/embed/PMI3_VIDEO_AQUI");
                                pmi3Aula5.setCurso(cursoPmi3);

                                Aula pmi3Aula6 = new Aula();
                                pmi3Aula6.setTitulo("Aula 6: Encerramento do Projeto ou Fase");
                                pmi3Aula6.setConteudo(
                                                "Finalizar todas as atividades para encerrar formalmente o projeto ou fase.");
                                pmi3Aula6.setVideoUrl("https://www.youtube.com/embed/PMI3_VIDEO_AQUI");
                                pmi3Aula6.setCurso(cursoPmi3);

                                todasAsAulas.addAll(List.of(pmi3Aula1, pmi3Aula2, pmi3Aula3, pmi3Aula4, pmi3Aula5,
                                                pmi3Aula6));

                                // --- AULAS PARA O CURSO DE ANÁLISE DE NEGÓCIO - BPM ---
                                Curso cursoBpm = cursoRepository.findByTitulo("Análise de Negócio - BPM").get();

                                Aula bpmAula1 = new Aula();
                                bpmAula1.setTitulo("Aula 1: Introdução ao BPM");
                                bpmAula1.setConteudo("Conceitos fundamentais de Business Process Management.");
                                bpmAula1.setVideoUrl("https://www.youtube.com/embed/BPM_VIDEO_AQUI");
                                bpmAula1.setGratuita(true);
                                bpmAula1.setCurso(cursoBpm);

                                Aula bpmAula2 = new Aula();
                                bpmAula2.setTitulo("Aula 2: Identificação de Processos");
                                bpmAula2.setConteudo(
                                                "Técnicas para identificação e delimitação de processos de negócio.");
                                bpmAula2.setVideoUrl("https://www.youtube.com/embed/BPM_VIDEO_AQUI");
                                bpmAula2.setCurso(cursoBpm);

                                Aula bpmAula3 = new Aula();
                                bpmAula3.setTitulo("Aula 3: Mapeamento de Processos");
                                bpmAula3.setConteudo("Técnicas de mapeamento e modelagem de processos AS-IS.");
                                bpmAula3.setVideoUrl("https://www.youtube.com/embed/BPM_VIDEO_AQUI");
                                bpmAula3.setCurso(cursoBpm);

                                Aula bpmAula4 = new Aula();
                                bpmAula4.setTitulo("Aula 4: Análise e Melhoria de Processos");
                                bpmAula4.setConteudo("Identificação de gargalos e oportunidades de melhoria (TO-BE).");
                                bpmAula4.setVideoUrl("https://www.youtube.com/embed/BPM_VIDEO_AQUI");
                                bpmAula4.setCurso(cursoBpm);

                                Aula bpmAula5 = new Aula();
                                bpmAula5.setTitulo("Aula 5: Implementação e Monitoramento");
                                bpmAula5.setConteudo(
                                                "Implementação de processos melhorados e monitoramento de performance.");
                                bpmAula5.setVideoUrl("https://www.youtube.com/embed/BPM_VIDEO_AQUI");
                                bpmAula5.setCurso(cursoBpm);

                                Aula bpmAula6 = new Aula();
                                bpmAula6.setTitulo("Aula 6: BPM e Transformação Digital");
                                bpmAula6.setConteudo("Como o BPM se integra às iniciativas de transformação digital.");
                                bpmAula6.setVideoUrl("https://www.youtube.com/embed/BPM_VIDEO_AQUI");
                                bpmAula6.setCurso(cursoBpm);

                                todasAsAulas.addAll(
                                                List.of(bpmAula1, bpmAula2, bpmAula3, bpmAula4, bpmAula5, bpmAula6));

                                // --- AULAS PARA O CURSO DE BPMN COM BIZAGI ---
                                Curso cursoBizagi = cursoRepository.findByTitulo("BPMN com Bizagi").get();

                                Aula bizagiAula1 = new Aula();
                                bizagiAula1.setTitulo("Aula 1: Introdução ao BPMN 2.0");
                                bizagiAula1.setConteudo("Conceitos fundamentais da notação BPMN 2.0 e seus elementos.");
                                bizagiAula1.setVideoUrl("https://www.youtube.com/embed/BIZAGI_VIDEO_AQUI");
                                bizagiAula1.setGratuita(true);
                                bizagiAula1.setCurso(cursoBizagi);

                                Aula bizagiAula2 = new Aula();
                                bizagiAula2.setTitulo("Aula 2: Conhecendo o Bizagi Modeler");
                                bizagiAula2.setConteudo("Interface e funcionalidades básicas do Bizagi Modeler.");
                                bizagiAula2.setVideoUrl("https://www.youtube.com/embed/BIZAGI_VIDEO_AQUI");
                                bizagiAula2.setCurso(cursoBizagi);

                                Aula bizagiAula3 = new Aula();
                                bizagiAula3.setTitulo("Aula 3: Elementos de Fluxo e Controle");
                                bizagiAula3.setConteudo("Eventos, atividades, gateways e sequência de fluxo.");
                                bizagiAula3.setVideoUrl("https://www.youtube.com/embed/BIZAGI_VIDEO_AQUI");
                                bizagiAula3.setCurso(cursoBizagi);

                                Aula bizagiAula4 = new Aula();
                                bizagiAula4.setTitulo("Aula 4: Elementos de Dados e Recursos");
                                bizagiAula4.setConteudo("Dados, mensagens, recursos humanos e artefatos.");
                                bizagiAula4.setVideoUrl("https://www.youtube.com/embed/BIZAGI_VIDEO_AQUI");
                                bizagiAula4.setCurso(cursoBizagi);

                                Aula bizagiAula5 = new Aula();
                                bizagiAula5.setTitulo("Aula 5: Modelagem de Processos Complexos");
                                bizagiAula5.setConteudo("Modelagem de processos com subprocessos, pools e lanes.");
                                bizagiAula5.setVideoUrl("https://www.youtube.com/embed/BIZAGI_VIDEO_AQUI");
                                bizagiAula5.setCurso(cursoBizagi);

                                Aula bizagiAula6 = new Aula();
                                bizagiAula6.setTitulo("Aula 6: Documentação e Simulação");
                                bizagiAula6.setConteudo("Documentação de processos e simulação de fluxos no Bizagi.");
                                bizagiAula6.setVideoUrl("https://www.youtube.com/embed/BIZAGI_VIDEO_AQUI");
                                bizagiAula6.setCurso(cursoBizagi);

                                todasAsAulas
                                                .addAll(List.of(bizagiAula1, bizagiAula2, bizagiAula3, bizagiAula4,
                                                                bizagiAula5, bizagiAula6));

                                // --- AULAS PARA O CURSO DE JIRA SOFTWARE ---
                                Curso cursoJira = cursoRepository
                                                .findByTitulo("Jira Software - Gestão Ágil de Projetos e Operações")
                                                .get();

                                Aula jiraAula1 = new Aula();
                                jiraAula1.setTitulo("Aula 1: Introdução ao Jira Software");
                                jiraAula1.setConteudo("Conhecendo a interface e os conceitos básicos do Jira.");
                                jiraAula1.setVideoUrl("https://www.youtube.com/embed/JIRA_VIDEO_AQUI");
                                jiraAula1.setGratuita(true);
                                jiraAula1.setCurso(cursoJira);

                                Aula jiraAula2 = new Aula();
                                jiraAula2.setTitulo("Aula 2: Configuração de Projetos");
                                jiraAula2.setConteudo(
                                                "Criação e configuração de projetos no Jira (Scrum, Kanban, etc).");
                                jiraAula2.setVideoUrl("https://www.youtube.com/embed/JIRA_VIDEO_AQUI");
                                jiraAula2.setCurso(cursoJira);

                                Aula jiraAula3 = new Aula();
                                jiraAula3.setTitulo("Aula 3: Gestão de Tarefas e Issues");
                                jiraAula3.setConteudo(
                                                "Criação, edição e transição de issues (tarefas, bugs, histórias).");
                                jiraAula3.setVideoUrl("https://www.youtube.com/embed/JIRA_VIDEO_AQUI");
                                jiraAula3.setCurso(cursoJira);

                                Aula jiraAula4 = new Aula();
                                jiraAula4.setTitulo("Aula 4: Boards e Sprints");
                                jiraAula4.setConteudo(
                                                "Configuração e utilização de boards Scrum e Kanban, planejamento de sprints.");
                                jiraAula4.setVideoUrl("https://www.youtube.com/embed/JIRA_VIDEO_AQUI");
                                jiraAula4.setCurso(cursoJira);

                                Aula jiraAula5 = new Aula();
                                jiraAula5.setTitulo("Aula 5: Relatórios e Métricas Ágeis");
                                jiraAula5.setConteudo(
                                                "Utilização dos relatórios do Jira (velocity, burn-down, cumulative flow).");
                                jiraAula5.setVideoUrl("https://www.youtube.com/embed/JIRA_VIDEO_AQUI");
                                jiraAula5.setCurso(cursoJira);

                                Aula jiraAula6 = new Aula();
                                jiraAula6.setTitulo("Aula 6: Personalização e Administração");
                                jiraAula6.setConteudo("Workflows personalizados, campos customizados e permissões.");
                                jiraAula6.setVideoUrl("https://www.youtube.com/embed/JIRA_VIDEO_AQUI");
                                jiraAula6.setCurso(cursoJira);

                                todasAsAulas.addAll(List.of(jiraAula1, jiraAula2, jiraAula3, jiraAula4, jiraAula5,
                                                jiraAula6));

                                // --- AULAS PARA O CURSO DE GESTÃO DE PROJETOS COM OKR ---
                                Curso cursoOkr = cursoRepository.findByTitulo("Gestão de Projetos com OKR").get();

                                Aula okrAula1 = new Aula();
                                okrAula1.setTitulo("Aula 1: Fundamentos do OKR");
                                okrAula1.setConteudo("O que são OKRs, origem e benefícios desta metodologia.");
                                okrAula1.setVideoUrl("https://www.youtube.com/embed/OKR_VIDEO_AQUI");
                                okrAula1.setGratuita(true);
                                okrAula1.setCurso(cursoOkr);

                                Aula okrAula2 = new Aula();
                                okrAula2.setTitulo("Aula 2: Definindo Objetivos (Objectives)");
                                okrAula2.setConteudo("Como criar objetivos inspiradores e alinhados à estratégia.");
                                okrAula2.setVideoUrl("https://www.youtube.com/embed/OKR_VIDEO_AQUI");
                                okrAula2.setCurso(cursoOkr);

                                Aula okrAula3 = new Aula();
                                okrAula3.setTitulo("Aula 3: Estabelecendo Resultados-Chave (Key Results)");
                                okrAula3.setConteudo("Como definir resultados-chave mensuráveis e desafiadores.");
                                okrAula3.setVideoUrl("https://www.youtube.com/embed/OKR_VIDEO_AQUI");
                                okrAula3.setCurso(cursoOkr);

                                Aula okrAula4 = new Aula();
                                okrAula4.setTitulo("Aula 4: Implementação de OKRs em Projetos");
                                okrAula4.setConteudo(
                                                "Como integrar OKRs no ciclo de vida de gerenciamento de projetos.");
                                okrAula4.setVideoUrl("https://www.youtube.com/embed/OKR_VIDEO_AQUI");
                                okrAula4.setCurso(cursoOkr);

                                Aula okrAula5 = new Aula();
                                okrAula5.setTitulo("Aula 5: Acompanhamento e Revisão de OKRs");
                                okrAula5.setConteudo("Check-ins regulares, pontuação e adaptação de OKRs.");
                                okrAula5.setVideoUrl("https://www.youtube.com/embed/OKR_VIDEO_AQUI");
                                okrAula5.setCurso(cursoOkr);

                                Aula okrAula6 = new Aula();
                                okrAula6.setTitulo("Aula 6: OKRs e Cultura Organizacional");
                                okrAula6.setConteudo(
                                                "Como os OKRs transformam a cultura e a transparência nas organizações.");
                                okrAula6.setVideoUrl("https://www.youtube.com/embed/OKR_VIDEO_AQUI");
                                okrAula6.setCurso(cursoOkr);

                                todasAsAulas.addAll(
                                                List.of(okrAula1, okrAula2, okrAula3, okrAula4, okrAula5, okrAula6));

                                // --- AULAS PARA O CURSO TRILHA MASTER ---
                                Curso cursoTrilha = cursoRepository.findByTitulo("Trilha Master").get();

                                Aula trilhaAula1 = new Aula();
                                trilhaAula1.setTitulo("Aula 1: Introdução à Trilha Master");
                                trilhaAula1.setConteudo("Visão geral da jornada de aprendizado e objetivos da trilha.");
                                trilhaAula1.setVideoUrl("https://www.youtube.com/embed/TRILHA_VIDEO_AQUI");
                                trilhaAula1.setGratuita(true);
                                trilhaAula1.setCurso(cursoTrilha);

                                Aula trilhaAula2 = new Aula();
                                trilhaAula2.setTitulo("Aula 2: Integração Power BI e Excel");
                                trilhaAula2.setConteudo("Como utilizar Power BI e Excel de forma complementar.");
                                trilhaAula2.setVideoUrl("https://www.youtube.com/embed/TRILHA_VIDEO_AQUI");
                                trilhaAula2.setCurso(cursoTrilha);

                                Aula trilhaAula3 = new Aula();
                                trilhaAula3.setTitulo("Aula 3: Dashboard Executivo");
                                trilhaAula3.setConteudo(
                                                "Criação de dashboard executivo integrando múltiplas fontes de dados.");
                                trilhaAula3.setVideoUrl("https://www.youtube.com/embed/TRILHA_VIDEO_AQUI");
                                trilhaAula3.setCurso(cursoTrilha);

                                Aula trilhaAula4 = new Aula();
                                trilhaAula4.setTitulo("Aula 4: Análise Preditiva com Power BI");
                                trilhaAula4.setConteudo(
                                                "Introdução à análise preditiva e machine learning no Power BI.");
                                trilhaAula4.setVideoUrl("https://www.youtube.com/embed/TRILHA_VIDEO_AQUI");
                                trilhaAula4.setCurso(cursoTrilha);

                                Aula trilhaAula5 = new Aula();
                                trilhaAula5.setTitulo("Aula 5: Automação de Processos com Power Automate");
                                trilhaAula5.setConteudo(
                                                "Automatizando fluxos de trabalho entre Excel, Power BI e outras ferramentas.");
                                trilhaAula5.setVideoUrl("https://www.youtube.com/embed/TRILHA_VIDEO_AQUI");
                                trilhaAula5.setCurso(cursoTrilha);

                                Aula trilhaAula6 = new Aula();
                                trilhaAula6.setTitulo("Aula 6: Projeto de Conclusão da Trilha");
                                trilhaAula6
                                                .setConteudo("Desenvolvimento de um projeto completo aplicando todos os conceitos aprendidos.");
                                trilhaAula6.setVideoUrl("https://www.youtube.com/embed/TRILHA_VIDEO_AQUI");
                                trilhaAula6.setCurso(cursoTrilha);

                                todasAsAulas
                                                .addAll(List.of(trilhaAula1, trilhaAula2, trilhaAula3, trilhaAula4,
                                                                trilhaAula5, trilhaAula6));

                                aulaRepository.saveAll(todasAsAulas);
                                System.out.println("Aulas reais de exemplo criadas.");
                        }

                        // --- 3. CRIAÇÃO DOS USUÁRIOS PADRÃO ---
                        if (usuarioRepository.findByUsername("aluno").isEmpty()) {
                                Usuario aluno = new Usuario();
                                aluno.setUsername("aluno");
                                aluno.setPassword(passwordEncoder.encode("senha123"));
                                aluno.setRole("ROLE_USER");
                                usuarioRepository.save(aluno);
                                System.out.println("Usuário 'aluno' criado.");
                        }

                        if (usuarioRepository.findByUsername("admin").isEmpty()) {
                                Usuario admin = new Usuario();
                                admin.setUsername("admin");
                                admin.setPassword(passwordEncoder.encode("adminmaster@pro"));
                                admin.setRole("ROLE_ADMIN");
                                usuarioRepository.save(admin);
                                System.out.println("Usuário 'admin' criado.");
                        }
                };
        }
}