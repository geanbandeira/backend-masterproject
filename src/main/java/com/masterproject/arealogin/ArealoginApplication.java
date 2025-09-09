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

                                Aula gestaoAula1 = new Aula();
                                gestaoAula1.setTitulo("Aula 1.1:");
                                gestaoAula1.setConteudo(
                                                "Nesta aula introdutória, exploramos os conceitos fundamentais da gestão híbrida de projetos, combinando metodologias tradicionais e ágeis.");
                                gestaoAula1.setVideoUrl(
                                                "https://masterproject-videos-cursos.s3.us-east-2.amazonaws.com/Aula%201.1%20-%20Treinamento%20-%20Master%20Project%20-%20Gest%C3%A3o%20de%20Projetos%20H%C3%ADbrida-20250315_151707-Meeting%20Recording.mp4?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=ASIATVCCOABD3SXLRM6W%2F20250908%2Fus-east-2%2Fs3%2Faws4_request&X-Amz-Date=20250908T190821Z&X-Amz-Expires=300&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEFsaCXVzLWVhc3QtMiJGMEQCIGDBsF0lpdpUF0VJT9TKdGqa2F2gu2St10KauJVmLsnFAiB5d0%2BavO2hSvidRIcDVgBnVlTfZulijis4O94fLt2P%2BSrNAwjE%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDI1MTM5NDkxNjQyMyIMhuSOF7TUkHTcRN8pKqEDSLnDJaZGoNYu%2BFxmrLAAyioOcSsEA2p%2BrX4s69qOzkTnBlkrb8DEN%2Bl3qsgogq57zlGqtsvOxZzCeOgy6L7TcX9GxIkeiAlUu1XQ4lET%2Bkpawi7opUj82eFoYZCDhbDTcUPOMM8aVqFAkEYrpnxPsGz9bFqCGqlb3mXZ8XiTdfAb%2FoAoAVtKxGOJ6h8yY7yE7p3JE8c46xk5G7YtiYCSWUhe%2BaDrymKB2UT4ov49kUOR6c0MxslHt2KBcZrVhpYgNbZgrWeKcmC9WX9%2FmPW0P%2Fi8lT%2FPhnl0mac69XVo9BTEksPUqMtVuzvlhyYdeC2YaAchC6DWOm%2Fnr%2FG%2ButhCtJbcAfToQdeYkUA2pJJzrTE1ARooun%2BLRsXYa%2F6QmNc2jLi%2BpYDyG3kuExN6rGekwIvH1NHpnHQJoHn4DruvkB8kY6stFqFGER70c8LfIOQxfzFNmDAlWT982TPWm5k5eJ1cIa5zweEsZ%2F0nkur3CK4YjZVh7HYC3WSXnbsf4Ta4hLMWEA%2Bxjcdmg%2FD1PzqCcrqBAKW09WQvltFRqC%2B7lFfvMNDQ%2FMUGOpUCKXUtKZ9%2BCoqFnam6gTr0pqlSucEMCeOIXH%2FDOv%2B71S%2BrIeF4svrvpmRaN6rbB9cni4HXxw8a8J442HZeAfv7EAcszZnzNGPy%2FvDpGDWFsGGQv0uZZdshZGF6SwjENdsJAuO8CFEtGCfzVQz%2FbhQgTxL0%2BfMQMR8OAmSBITTwaaxhDfPxVbifA6Z0QIYttOz54KhAD0uQibtiDSpWc%2Bs5miZ5vclGyi1DcY8tFncWyyq43cytdXaxKS%2FKWPe82JcxGJ0xaKgYjGoG3M%2FMZtAYfiY1zzxD3chD8WVuT0PYO5h1rVswyt%2BzJ2e1tvElDUiNPcMZayNnPbvH6086lfx6wIU21Z7GsrgxthXUP1Ki2Yf0RmNsPg%3D%3D&X-Amz-Signature=957afc4a8823024f063f11dab2a4acbad8ad7f19762c84da8190a26a1b67f8fd&X-Amz-SignedHeaders=host&response-content-disposition=inline");
                                gestaoAula1.setGratuita(true);
                                gestaoAula1.setCurso(cursoGestao);

                                Aula gestaoAula2 = new Aula();
                                gestaoAula2.setTitulo("Aula 1.2:");
                                gestaoAula2.setConteudo(
                                                "Analisando as diferenças entre as abordagens tradicional e ágil, e quando usar cada metodologia.");
                                gestaoAula2.setVideoUrl(
                                                "https://masterproject-videos-cursos.s3.us-east-2.amazonaws.com/Aula%201.2%20-%20Treinamento%20-%20Master%20Project%20-%20Gest%C3%A3o%20de%20Projetos%20H%C3%ADbrida-20250315_191240-Meeting%20Recording.mp4?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=ASIATVCCOABDUAOY6CVQ%2F20250908%2Fus-east-2%2Fs3%2Faws4_request&X-Amz-Date=20250908T191342Z&X-Amz-Expires=300&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEFsaCXVzLWVhc3QtMiJIMEYCIQDnv6%2F%2Bl72ksbLlY045i8g5G7NMsivPupigVt4FhcxugAIhALF9QOTLqzuTMLFgsEHZKDdJ63h8nfXwd58koUgLjp9TKs0DCMT%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEQABoMMjUxMzk0OTE2NDIzIgyzR8Zs5doiNcpUtCYqoQPdS3r6kZUmfOs8vq8Or97FrnnnH9kl5wUyTI0jegMGsdVjmRzZRwILqgxVMOtWUhLx8c0UwtDM%2FHBb0PvqnPrdVcKocXC87%2FzY%2B0VbWbKar6H4Pva5bmy19LPzMD%2BKDGVSN9upT25CbnC23haySAP3ap0ITr3EtZrmsxvLwmfhd2oVrfSIiO%2B22KETaABWfcJgJUE2t2cAmCDz%2BBGa5BxfrhqR5hGm0XE4sflp3hXFPR6fMA3fq0rImVx5o2ENBaiAwH%2Bd08dd9z5XdlDAny23ywReZThmDIi%2FNaktWTEc0SEckCdw7G%2BT24NG5V4H5jkBXtvtIdgrWkqwZQDYFE1i3Uo6bAqYko6Byj6gHhmY5fvfdQAeXcbex5X76kLPgS3dxx8vwQCyx5mf8BmTiiM0rPsIanxPrvwaOWzSOx9hR8iJqladtiBDF6k51vjj1855KbnhejKO0tbcBQQyxHOjJ3%2Fot%2BvZj8UjdttpbCZ%2BeswfuK8MflSks7ZNaPp%2FIX4N9jFoZhdkidbT2fGyZxwOi2UbRH3SDdahIqaCQCJ5MP0w0ND8xQY6kwJraZi6bDJnpEgcvB1pLto1SfOX%2F3tc8mPzdsiOCt6sehlH%2BBdnDWWqJlFqBcmftWcw7N4R2BLt%2Bxrn7DRy0UBpHjkHsjj%2Fd%2F1c5dL2liqCtCbItJ3EgC7PZfcW9%2FK7pfQVkMolmI439JtLgsPwDTRwyxRYgwgY0qWJXOWYedd7dylZB0RkpCE1bkAWUGCffnHe2miptt43xFp2%2BlqVHjfb%2B8B6vVR%2F%2Ffhl82skcZi%2FV%2Fn5P2maRFrDAvhzIShAi6xnXxWAJFA5w%2FyTPIjDwETr8dRt%2F9puQZWJPqrDT9gn4CVeDKe6D78M89lL9L5bVQMwCbWHhWo%2BUul5QsNpLieSKZvTpCr9Ldq95v3jslohscfokg%3D%3D&X-Amz-Signature=de876f41f52fb9f2b094bea3a27ed7b63c2d6c652a7d684d007e8fd54d2fc50c&X-Amz-SignedHeaders=host&response-content-disposition=inline");
                                gestaoAula2.setCurso(cursoGestao);

                                Aula gestaoAula3 = new Aula();
                                gestaoAula3.setTitulo("Aula 1.3:");
                                gestaoAula3.setConteudo(
                                                "Passo a passo para implementar a gestão híbrida no seu time, com exemplos práticos.");
                                gestaoAula3.setVideoUrl(
                                                "https://masterproject-videos-cursos.s3.us-east-2.amazonaws.com/Aula%201.3%20-%20Treinamento%20-%20Master%20Project%20-%20Gest%C3%A3o%20de%20Projetos%20H%C3%ADbrida-20250315_210112-Meeting%20Recording.mp4?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=ASIATVCCOABDUAOY6CVQ%2F20250908%2Fus-east-2%2Fs3%2Faws4_request&X-Amz-Date=20250908T191858Z&X-Amz-Expires=300&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEFsaCXVzLWVhc3QtMiJIMEYCIQDnv6%2F%2Bl72ksbLlY045i8g5G7NMsivPupigVt4FhcxugAIhALF9QOTLqzuTMLFgsEHZKDdJ63h8nfXwd58koUgLjp9TKs0DCMT%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEQABoMMjUxMzk0OTE2NDIzIgyzR8Zs5doiNcpUtCYqoQPdS3r6kZUmfOs8vq8Or97FrnnnH9kl5wUyTI0jegMGsdVjmRzZRwILqgxVMOtWUhLx8c0UwtDM%2FHBb0PvqnPrdVcKocXC87%2FzY%2B0VbWbKar6H4Pva5bmy19LPzMD%2BKDGVSN9upT25CbnC23haySAP3ap0ITr3EtZrmsxvLwmfhd2oVrfSIiO%2B22KETaABWfcJgJUE2t2cAmCDz%2BBGa5BxfrhqR5hGm0XE4sflp3hXFPR6fMA3fq0rImVx5o2ENBaiAwH%2Bd08dd9z5XdlDAny23ywReZThmDIi%2FNaktWTEc0SEckCdw7G%2BT24NG5V4H5jkBXtvtIdgrWkqwZQDYFE1i3Uo6bAqYko6Byj6gHhmY5fvfdQAeXcbex5X76kLPgS3dxx8vwQCyx5mf8BmTiiM0rPsIanxPrvwaOWzSOx9hR8iJqladtiBDF6k51vjj1855KbnhejKO0tbcBQQyxHOjJ3%2Fot%2BvZj8UjdttpbCZ%2BeswfuK8MflSks7ZNaPp%2FIX4N9jFoZhdkidbT2fGyZxwOi2UbRH3SDdahIqaCQCJ5MP0w0ND8xQY6kwJraZi6bDJnpEgcvB1pLto1SfOX%2F3tc8mPzdsiOCt6sehlH%2BBdnDWWqJlFqBcmftWcw7N4R2BLt%2Bxrn7DRy0UBpHjkHsjj%2Fd%2F1c5dL2liqCtCbItJ3EgC7PZfcW9%2FK7pfQVkMolmI439JtLgsPwDTRwyxRYgwgY0qWJXOWYedd7dylZB0RkpCE1bkAWUGCffnHe2miptt43xFp2%2BlqVHjfb%2B8B6vVR%2F%2Ffhl82skcZi%2FV%2Fn5P2maRFrDAvhzIShAi6xnXxWAJFA5w%2FyTPIjDwETr8dRt%2F9puQZWJPqrDT9gn4CVeDKe6D78M89lL9L5bVQMwCbWHhWo%2BUul5QsNpLieSKZvTpCr9Ldq95v3jslohscfokg%3D%3D&X-Amz-Signature=7f6a487987e40b33911185a0daf3a5750240135620a6d9ff56c07ecd3f63e427&X-Amz-SignedHeaders=host&response-content-disposition=inline");
                                gestaoAula3.setCurso(cursoGestao);

                                Aula gestaoAula4 = new Aula();
                                gestaoAula4.setTitulo("Aula 2.1:");
                                gestaoAula4.setConteudo(
                                                "Conheça as ferramentas que facilitam o gerenciamento híbrido de projetos.");
                                gestaoAula4.setVideoUrl(
                                                "https://masterproject-videos-cursos.s3.us-east-2.amazonaws.com/Aula%202.1%20-%20Treinamento%20-%20Master%20Project%20-%20Gest%C3%A3o%20de%20Projetos%20H%C3%ADbrida-20250322_150647-Meeting%20Recording.mp4?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=ASIATVCCOABD73EHT37N%2F20250908%2Fus-east-2%2Fs3%2Faws4_request&X-Amz-Date=20250908T192710Z&X-Amz-Expires=300&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEFsaCXVzLWVhc3QtMiJGMEQCIGGDTPYpjreWpZbkdRMrDR6OxCX2749%2Baz9lBqZDV1JlAiA9jZm%2BWvOeHRr7liS%2Bn1y%2BofWrBnsV9HIY6zaiIQBmwSrNAwjF%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDI1MTM5NDkxNjQyMyIMDF6shxltIaNm7hlUKqEDrRAArzrtvYARjFr7Ekx2HVsVz7fMxA7UgucnOEPMXY%2FI%2FJZVgMNDjBlPu98MJVj7XSSKVi3kF6kImOY9E93R2c4dGhBCIJlgWF57Ya2TXBrv2No8ifixoG8dnIC5pgc8GGwDfrwW0D5wJsbSllWH9Zea%2BcJ7HBpC6Xi25LSPqVy0bwCraw6eIFild6cnvk2UM1zfwC7NJFP4L0VgueitF0EyQXAdGT4gh9KlPWkruQ90PvKRIIyXlquzykAPjSxj170ayMNdPN31TN47SYGxZuO9%2FMki3lIJBRcjie%2BFsvgDHSQlcgJSO1vFzvsajW1Lx9iwnxomfXYrWdgKsnqjst17uJ39hJU2A%2Bkto9IVxyhkF2mQpjo0wRNeBS9d1N2CBEV6ouhv0%2FdtGmazVkOmpahyD04MpsC9IlvAaEBJ1Y0RjXRKA3LGM1bWRa94CB2AcwDJMlqqcaHsOnisnCumCgFU8ksmzHKsHXm0zR3tsDW3uKgnL0HB8QR5i0K0SKcbAq8iWs5UtPIyC6XOwKQVfSXEoAUi3VW4V7ZLF%2FekAmZRMNDQ%2FMUGOpUCHr%2B6Tr8o%2F1YBwShEvoGzuUiX5OlZ%2BLklmphpRI4y%2FUaEcB0mOhu81ogKi6pCzCcmLhUYtm0t%2FlYPemOd51GQy4MR8QYANKV6XAcaHlwLC2ZDPdR4XhdaPdpAD60L7CK9AYY3PE03CEho8mV0DCGBid2xa1evLfOUG6JJRPx9lwJig%2F9ZXP8TktVj6L6N9tR9yGPvBtDBKjbWdp219Ap2l2U%2BNNdQm0WoMytW5TW9IME1AHxTh2ig03QI60zHS3yGjJqdBbzY0nzLjivO9QQJ7cSTBTBsIiZ4iTC%2BtvTRgjn5L%2BDka1LhuH3vzfTmuPA9aJu9RFjwLh0JUcjRUcPhyszEyguLWjcDt4V1tia8yFK5FOD92g%3D%3D&X-Amz-Signature=a03b0928586bd719d7a2ee23487f34f6ac578d35a215d7be139e422069808f46&X-Amz-SignedHeaders=host&response-content-disposition=inline");
                                gestaoAula4.setCurso(cursoGestao);

                                Aula gestaoAula5 = new Aula();
                                gestaoAula5.setTitulo("Aula 2.2:");
                                gestaoAula5.setConteudo(
                                                "Como medir o sucesso de um projeto gerenciado de forma híbrida, com KPIs específicos.");
                                gestaoAula5.setVideoUrl(
                                                "https://masterproject-videos-cursos.s3.us-east-2.amazonaws.com/Aula%202.2%20-%20Treinamento%20-%20Master%20Project%20-%20Gest%C3%A3o%20de%20Projetos%20H%C3%ADbrida-20250322_190640-Meeting%20Recording.mp4?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=ASIATVCCOABD73EHT37N%2F20250908%2Fus-east-2%2Fs3%2Faws4_request&X-Amz-Date=20250908T193212Z&X-Amz-Expires=300&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEFsaCXVzLWVhc3QtMiJGMEQCIGGDTPYpjreWpZbkdRMrDR6OxCX2749%2Baz9lBqZDV1JlAiA9jZm%2BWvOeHRr7liS%2Bn1y%2BofWrBnsV9HIY6zaiIQBmwSrNAwjF%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDI1MTM5NDkxNjQyMyIMDF6shxltIaNm7hlUKqEDrRAArzrtvYARjFr7Ekx2HVsVz7fMxA7UgucnOEPMXY%2FI%2FJZVgMNDjBlPu98MJVj7XSSKVi3kF6kImOY9E93R2c4dGhBCIJlgWF57Ya2TXBrv2No8ifixoG8dnIC5pgc8GGwDfrwW0D5wJsbSllWH9Zea%2BcJ7HBpC6Xi25LSPqVy0bwCraw6eIFild6cnvk2UM1zfwC7NJFP4L0VgueitF0EyQXAdGT4gh9KlPWkruQ90PvKRIIyXlquzykAPjSxj170ayMNdPN31TN47SYGxZuO9%2FMki3lIJBRcjie%2BFsvgDHSQlcgJSO1vFzvsajW1Lx9iwnxomfXYrWdgKsnqjst17uJ39hJU2A%2Bkto9IVxyhkF2mQpjo0wRNeBS9d1N2CBEV6ouhv0%2FdtGmazVkOmpahyD04MpsC9IlvAaEBJ1Y0RjXRKA3LGM1bWRa94CB2AcwDJMlqqcaHsOnisnCumCgFU8ksmzHKsHXm0zR3tsDW3uKgnL0HB8QR5i0K0SKcbAq8iWs5UtPIyC6XOwKQVfSXEoAUi3VW4V7ZLF%2FekAmZRMNDQ%2FMUGOpUCHr%2B6Tr8o%2F1YBwShEvoGzuUiX5OlZ%2BLklmphpRI4y%2FUaEcB0mOhu81ogKi6pCzCcmLhUYtm0t%2FlYPemOd51GQy4MR8QYANKV6XAcaHlwLC2ZDPdR4XhdaPdpAD60L7CK9AYY3PE03CEho8mV0DCGBid2xa1evLfOUG6JJRPx9lwJig%2F9ZXP8TktVj6L6N9tR9yGPvBtDBKjbWdp219Ap2l2U%2BNNdQm0WoMytW5TW9IME1AHxTh2ig03QI60zHS3yGjJqdBbzY0nzLjivO9QQJ7cSTBTBsIiZ4iTC%2BtvTRgjn5L%2BDka1LhuH3vzfTmuPA9aJu9RFjwLh0JUcjRUcPhyszEyguLWjcDt4V1tia8yFK5FOD92g%3D%3D&X-Amz-Signature=b41c7cdee6ef9d613fb946a178873030eeaf37a6c113f5c00811686bd2c71147&X-Amz-SignedHeaders=host&response-content-disposition=inline");
                                gestaoAula5.setCurso(cursoGestao);

                                Aula gestaoAula6 = new Aula();
                                gestaoAula6.setTitulo("Aula 2.3:");
                                gestaoAula6.setConteudo( "Analisando um projeto real que utilizou a abordagem híbrida com sucesso.");
                                gestaoAula6.setVideoUrl("https://masterproject-videos-cursos.s3.us-east-2.amazonaws.com/Aula%202.3%20-%20Treinamento%20-%20Master%20Project%20-%20Gest%C3%A3o%20de%20Projetos%20H%C3%ADbrida-20250322_210211-Meeting%20Recording.mp4?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=ASIATVCCOABDYCJR6OX6%2F20250908%2Fus-east-2%2Fs3%2Faws4_request&X-Amz-Date=20250908T194455Z&X-Amz-Expires=300&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEFwaCXVzLWVhc3QtMiJGMEQCH032ZdpZ%2BT0lFsuC3W49QCQqWnZhN4qzmPlqX3qDDyUCIQCFfpYWkVm9ZVkDEkdk8SyR8s%2FfEhRrZAsY3nQF2N1A%2BCrNAwjF%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDI1MTM5NDkxNjQyMyIMrkUym92xsD%2BN6XrWKqED6xqHHVsqWRvrCKttpypAUvrRfci29ekJWM42SPiIhQKib9ooJo8M952wnIQy0972SYCxn7GEpX9niVkYR3BAVwciNEE%2BMTPz0ws1mSull3FEalwhiIlYg7lfxMsnwxCSAGoLUZ5XYkvmVv4aY1nM1DFY3uFdpS8jXCYH3sbgPaxaC55ZF7P9RZdhm2XB5L9PevMjlg4nQlcWegCzERJvNAe4vcn27tEljy%2FY7gi1XHD8HymblviBJyoCULmgOoA4U%2F9mwgQxOdFWlCUDTqoFwsVRC2Xz4ffR710biKTI2mEHaQIsj8BY6Z1EN7FAM6wkVHqC4y5IcaI6xIkmY3MAuodTqxe1T0Pz%2BXmhSJEcoJWGvlIoyS4uGEsASCpVsbM6Jwgw0XhhxKuiApXa1IidCbWBcNLlMoxclguu91JtkY%2FnH5JxmSr2FGKhP8YhGtef%2BLRpVs3BKm3uk7spzwbpxmKKQYFQnSWFxA5ytnHykpbfju8vasDWPRXHNYLE38Qgx3USRaQBMBOg3ycntiAoDjd%2BTww4Ab7%2FncTgLHG2Nb2CMNDQ%2FMUGOpUCvMWlCKK71xUOjHPDP4ag%2BFq2LSTqf%2BV1eRnnayJYb4befkJMF1IhqSyXv9xAomZqNlHdo8%2F0cwE8bKnQI2s1CdaVVQCPm51tOVJ70m2JE%2Bw0MqRtiPay0xNcIhxvoQMrg0uG%2FL%2BOWVi2Piwt9cMH%2Bk2k%2FxRGEF78Q1aOfLeYunQyNmUsJRGV89u8kF77VPyIQ5pv1RmDr5OADvtxhWvD8vPn3GLu3xRVe1OIQL%2BB4bUvcC7Pl7Dv9mZrdizr1Sj0udM11iAjP9not0QTtMEtXJvlRUnB97nsoS2MJwpUx0C%2FKrE7nb1x57C1EIK5SaEA2cH%2BIzR4rFKjaHGuZqRlluYSrFMgKHcUvpOsWeUrDID9aWMZSw%3D%3D&X-Amz-Signature=5c5923b52701ac78f0e13d2d9a334b68f25a946b3b0ccd09d7fa8683e5246ac3&X-Amz-SignedHeaders=host&response-content-disposition=inline");
                                gestaoAula6.setCurso(cursoGestao);

                                Aula gestaoAula77 = new Aula();
                                gestaoAula77.setTitulo("Aula 3.1:");
                                gestaoAula77.setConteudo("Como definir escopo e cronograma em um projeto.");
                                gestaoAula77.setVideoUrl("https://masterproject-videos-cursos.s3.us-east-2.amazonaws.com/Aula%203.1%20-%20Treinamento%20-%20Master%20Project%20-%20Gest%C3%A3o%20de%20Projetos%20H%C3%ADbrida-20250329_152149-Meeting%20Recording.mp4?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=ASIATVCCOABDYCJR6OX6%2F20250908%2Fus-east-2%2Fs3%2Faws4_request&X-Amz-Date=20250908T194748Z&X-Amz-Expires=300&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEFwaCXVzLWVhc3QtMiJGMEQCH032ZdpZ%2BT0lFsuC3W49QCQqWnZhN4qzmPlqX3qDDyUCIQCFfpYWkVm9ZVkDEkdk8SyR8s%2FfEhRrZAsY3nQF2N1A%2BCrNAwjF%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDI1MTM5NDkxNjQyMyIMrkUym92xsD%2BN6XrWKqED6xqHHVsqWRvrCKttpypAUvrRfci29ekJWM42SPiIhQKib9ooJo8M952wnIQy0972SYCxn7GEpX9niVkYR3BAVwciNEE%2BMTPz0ws1mSull3FEalwhiIlYg7lfxMsnwxCSAGoLUZ5XYkvmVv4aY1nM1DFY3uFdpS8jXCYH3sbgPaxaC55ZF7P9RZdhm2XB5L9PevMjlg4nQlcWegCzERJvNAe4vcn27tEljy%2FY7gi1XHD8HymblviBJyoCULmgOoA4U%2F9mwgQxOdFWlCUDTqoFwsVRC2Xz4ffR710biKTI2mEHaQIsj8BY6Z1EN7FAM6wkVHqC4y5IcaI6xIkmY3MAuodTqxe1T0Pz%2BXmhSJEcoJWGvlIoyS4uGEsASCpVsbM6Jwgw0XhhxKuiApXa1IidCbWBcNLlMoxclguu91JtkY%2FnH5JxmSr2FGKhP8YhGtef%2BLRpVs3BKm3uk7spzwbpxmKKQYFQnSWFxA5ytnHykpbfju8vasDWPRXHNYLE38Qgx3USRaQBMBOg3ycntiAoDjd%2BTww4Ab7%2FncTgLHG2Nb2CMNDQ%2FMUGOpUCvMWlCKK71xUOjHPDP4ag%2BFq2LSTqf%2BV1eRnnayJYb4befkJMF1IhqSyXv9xAomZqNlHdo8%2F0cwE8bKnQI2s1CdaVVQCPm51tOVJ70m2JE%2Bw0MqRtiPay0xNcIhxvoQMrg0uG%2FL%2BOWVi2Piwt9cMH%2Bk2k%2FxRGEF78Q1aOfLeYunQyNmUsJRGV89u8kF77VPyIQ5pv1RmDr5OADvtxhWvD8vPn3GLu3xRVe1OIQL%2BB4bUvcC7Pl7Dv9mZrdizr1Sj0udM11iAjP9not0QTtMEtXJvlRUnB97nsoS2MJwpUx0C%2FKrE7nb1x57C1EIK5SaEA2cH%2BIzR4rFKjaHGuZqRlluYSrFMgKHcUvpOsWeUrDID9aWMZSw%3D%3D&X-Amz-Signature=4bb3e4b543b76df7683cad06f79973cea1f2881bb361f6d945ac1ad1e26bd7fa&X-Amz-SignedHeaders=host&response-content-disposition=inline");
                                gestaoAula77.setCurso(cursoGestao);
                                
                                Aula gestaoAula7 = new Aula();
                                gestaoAula7.setTitulo("Aula 3.2:");
                                gestaoAula7.setConteudo("Como definir escopo e cronograma em um projeto.");
                                gestaoAula7.setVideoUrl("https://masterproject-videos-cursos.s3.us-east-2.amazonaws.com/Aula%203.2%20-%20Treinamento%20-%20Master%20Project%20-%20Gest%C3%A3o%20de%20Projetos%20H%C3%ADbrida-20250329_181608-Meeting%20Recording.mp4?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=ASIATVCCOABDYCJR6OX6%2F20250908%2Fus-east-2%2Fs3%2Faws4_request&X-Amz-Date=20250908T194806Z&X-Amz-Expires=300&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEFwaCXVzLWVhc3QtMiJGMEQCH032ZdpZ%2BT0lFsuC3W49QCQqWnZhN4qzmPlqX3qDDyUCIQCFfpYWkVm9ZVkDEkdk8SyR8s%2FfEhRrZAsY3nQF2N1A%2BCrNAwjF%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDI1MTM5NDkxNjQyMyIMrkUym92xsD%2BN6XrWKqED6xqHHVsqWRvrCKttpypAUvrRfci29ekJWM42SPiIhQKib9ooJo8M952wnIQy0972SYCxn7GEpX9niVkYR3BAVwciNEE%2BMTPz0ws1mSull3FEalwhiIlYg7lfxMsnwxCSAGoLUZ5XYkvmVv4aY1nM1DFY3uFdpS8jXCYH3sbgPaxaC55ZF7P9RZdhm2XB5L9PevMjlg4nQlcWegCzERJvNAe4vcn27tEljy%2FY7gi1XHD8HymblviBJyoCULmgOoA4U%2F9mwgQxOdFWlCUDTqoFwsVRC2Xz4ffR710biKTI2mEHaQIsj8BY6Z1EN7FAM6wkVHqC4y5IcaI6xIkmY3MAuodTqxe1T0Pz%2BXmhSJEcoJWGvlIoyS4uGEsASCpVsbM6Jwgw0XhhxKuiApXa1IidCbWBcNLlMoxclguu91JtkY%2FnH5JxmSr2FGKhP8YhGtef%2BLRpVs3BKm3uk7spzwbpxmKKQYFQnSWFxA5ytnHykpbfju8vasDWPRXHNYLE38Qgx3USRaQBMBOg3ycntiAoDjd%2BTww4Ab7%2FncTgLHG2Nb2CMNDQ%2FMUGOpUCvMWlCKK71xUOjHPDP4ag%2BFq2LSTqf%2BV1eRnnayJYb4befkJMF1IhqSyXv9xAomZqNlHdo8%2F0cwE8bKnQI2s1CdaVVQCPm51tOVJ70m2JE%2Bw0MqRtiPay0xNcIhxvoQMrg0uG%2FL%2BOWVi2Piwt9cMH%2Bk2k%2FxRGEF78Q1aOfLeYunQyNmUsJRGV89u8kF77VPyIQ5pv1RmDr5OADvtxhWvD8vPn3GLu3xRVe1OIQL%2BB4bUvcC7Pl7Dv9mZrdizr1Sj0udM11iAjP9not0QTtMEtXJvlRUnB97nsoS2MJwpUx0C%2FKrE7nb1x57C1EIK5SaEA2cH%2BIzR4rFKjaHGuZqRlluYSrFMgKHcUvpOsWeUrDID9aWMZSw%3D%3D&X-Amz-Signature=066cf86b8b0ec440277e2d2a639b9e53ab1cc797fbe0d7dab210049a15201d1a&X-Amz-SignedHeaders=host&response-content-disposition=inline");
                                gestaoAula7.setCurso(cursoGestao);

                                Aula gestaoAula8 = new Aula();
                                gestaoAula8.setTitulo("Aula 3.3:");
                                gestaoAula8.setConteudo("Planejamento e controle de custos e garantia de qualidade.");
                                gestaoAula8.setVideoUrl("https://masterproject-videos-cursos.s3.us-east-2.amazonaws.com/Aula%203.3%20-%20Treinamento%20-%20Master%20Project%20-%20Gest%C3%A3o%20de%20Projetos%20H%C3%ADbrida-20250329_210307-Meeting%20Recording.mp4?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=ASIATVCCOABDYCJR6OX6%2F20250908%2Fus-east-2%2Fs3%2Faws4_request&X-Amz-Date=20250908T194831Z&X-Amz-Expires=300&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEFwaCXVzLWVhc3QtMiJGMEQCH032ZdpZ%2BT0lFsuC3W49QCQqWnZhN4qzmPlqX3qDDyUCIQCFfpYWkVm9ZVkDEkdk8SyR8s%2FfEhRrZAsY3nQF2N1A%2BCrNAwjF%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDI1MTM5NDkxNjQyMyIMrkUym92xsD%2BN6XrWKqED6xqHHVsqWRvrCKttpypAUvrRfci29ekJWM42SPiIhQKib9ooJo8M952wnIQy0972SYCxn7GEpX9niVkYR3BAVwciNEE%2BMTPz0ws1mSull3FEalwhiIlYg7lfxMsnwxCSAGoLUZ5XYkvmVv4aY1nM1DFY3uFdpS8jXCYH3sbgPaxaC55ZF7P9RZdhm2XB5L9PevMjlg4nQlcWegCzERJvNAe4vcn27tEljy%2FY7gi1XHD8HymblviBJyoCULmgOoA4U%2F9mwgQxOdFWlCUDTqoFwsVRC2Xz4ffR710biKTI2mEHaQIsj8BY6Z1EN7FAM6wkVHqC4y5IcaI6xIkmY3MAuodTqxe1T0Pz%2BXmhSJEcoJWGvlIoyS4uGEsASCpVsbM6Jwgw0XhhxKuiApXa1IidCbWBcNLlMoxclguu91JtkY%2FnH5JxmSr2FGKhP8YhGtef%2BLRpVs3BKm3uk7spzwbpxmKKQYFQnSWFxA5ytnHykpbfju8vasDWPRXHNYLE38Qgx3USRaQBMBOg3ycntiAoDjd%2BTww4Ab7%2FncTgLHG2Nb2CMNDQ%2FMUGOpUCvMWlCKK71xUOjHPDP4ag%2BFq2LSTqf%2BV1eRnnayJYb4befkJMF1IhqSyXv9xAomZqNlHdo8%2F0cwE8bKnQI2s1CdaVVQCPm51tOVJ70m2JE%2Bw0MqRtiPay0xNcIhxvoQMrg0uG%2FL%2BOWVi2Piwt9cMH%2Bk2k%2FxRGEF78Q1aOfLeYunQyNmUsJRGV89u8kF77VPyIQ5pv1RmDr5OADvtxhWvD8vPn3GLu3xRVe1OIQL%2BB4bUvcC7Pl7Dv9mZrdizr1Sj0udM11iAjP9not0QTtMEtXJvlRUnB97nsoS2MJwpUx0C%2FKrE7nb1x57C1EIK5SaEA2cH%2BIzR4rFKjaHGuZqRlluYSrFMgKHcUvpOsWeUrDID9aWMZSw%3D%3D&X-Amz-Signature=127db67d0f7fc9b290dd9836b797b38d4a56ac0e8e0368ff7af416a2c67b7123&X-Amz-SignedHeaders=host&response-content-disposition=inline");
                                gestaoAula8.setCurso(cursoGestao);

                                // Aulas Módulo 4
                                Aula gestaoAula9 = new Aula();
                                gestaoAula9.setTitulo("Aula 4.1:");
                                gestaoAula9.setConteudo("Formação de equipes de alto desempenho.");
                                gestaoAula9.setVideoUrl("https://masterproject-videos-cursos.s3.us-east-2.amazonaws.com/Aula%204.1%20-%20Treinamento%20-%20Master%20Project%20-%20Gest%C3%A3o%20de%20Projetos%20H%C3%ADbrida-20250405_152000-Meeting%20Recording.mp4?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=ASIATVCCOABDYCJR6OX6%2F20250908%2Fus-east-2%2Fs3%2Faws4_request&X-Amz-Date=20250908T194854Z&X-Amz-Expires=300&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEFwaCXVzLWVhc3QtMiJGMEQCH032ZdpZ%2BT0lFsuC3W49QCQqWnZhN4qzmPlqX3qDDyUCIQCFfpYWkVm9ZVkDEkdk8SyR8s%2FfEhRrZAsY3nQF2N1A%2BCrNAwjF%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDI1MTM5NDkxNjQyMyIMrkUym92xsD%2BN6XrWKqED6xqHHVsqWRvrCKttpypAUvrRfci29ekJWM42SPiIhQKib9ooJo8M952wnIQy0972SYCxn7GEpX9niVkYR3BAVwciNEE%2BMTPz0ws1mSull3FEalwhiIlYg7lfxMsnwxCSAGoLUZ5XYkvmVv4aY1nM1DFY3uFdpS8jXCYH3sbgPaxaC55ZF7P9RZdhm2XB5L9PevMjlg4nQlcWegCzERJvNAe4vcn27tEljy%2FY7gi1XHD8HymblviBJyoCULmgOoA4U%2F9mwgQxOdFWlCUDTqoFwsVRC2Xz4ffR710biKTI2mEHaQIsj8BY6Z1EN7FAM6wkVHqC4y5IcaI6xIkmY3MAuodTqxe1T0Pz%2BXmhSJEcoJWGvlIoyS4uGEsASCpVsbM6Jwgw0XhhxKuiApXa1IidCbWBcNLlMoxclguu91JtkY%2FnH5JxmSr2FGKhP8YhGtef%2BLRpVs3BKm3uk7spzwbpxmKKQYFQnSWFxA5ytnHykpbfju8vasDWPRXHNYLE38Qgx3USRaQBMBOg3ycntiAoDjd%2BTww4Ab7%2FncTgLHG2Nb2CMNDQ%2FMUGOpUCvMWlCKK71xUOjHPDP4ag%2BFq2LSTqf%2BV1eRnnayJYb4befkJMF1IhqSyXv9xAomZqNlHdo8%2F0cwE8bKnQI2s1CdaVVQCPm51tOVJ70m2JE%2Bw0MqRtiPay0xNcIhxvoQMrg0uG%2FL%2BOWVi2Piwt9cMH%2Bk2k%2FxRGEF78Q1aOfLeYunQyNmUsJRGV89u8kF77VPyIQ5pv1RmDr5OADvtxhWvD8vPn3GLu3xRVe1OIQL%2BB4bUvcC7Pl7Dv9mZrdizr1Sj0udM11iAjP9not0QTtMEtXJvlRUnB97nsoS2MJwpUx0C%2FKrE7nb1x57C1EIK5SaEA2cH%2BIzR4rFKjaHGuZqRlluYSrFMgKHcUvpOsWeUrDID9aWMZSw%3D%3D&X-Amz-Signature=d1f7060571e4890cb8a1ae72c9db6385bdc62126644c2511e34dc4ec80f0b948&X-Amz-SignedHeaders=host&response-content-disposition=inline");
                                gestaoAula9.setCurso(cursoGestao);

                                Aula gestaoAula10 = new Aula();
                                gestaoAula10.setTitulo("Aula 4.2:");
                                gestaoAula10.setConteudo("Ferramentas e técnicas para melhorar a comunicação no projeto.");
                                gestaoAula10.setVideoUrl("https://masterproject-videos-cursos.s3.us-east-2.amazonaws.com/Aula%204.2%20-%20Treinamento%20-%20Master%20Project%20-%20Gest%C3%A3o%20de%20Projetos%20H%C3%ADbrida-20250405_192001-Meeting%20Recording.mp4?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=ASIATVCCOABDYCJR6OX6%2F20250908%2Fus-east-2%2Fs3%2Faws4_request&X-Amz-Date=20250908T194930Z&X-Amz-Expires=300&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEFwaCXVzLWVhc3QtMiJGMEQCH032ZdpZ%2BT0lFsuC3W49QCQqWnZhN4qzmPlqX3qDDyUCIQCFfpYWkVm9ZVkDEkdk8SyR8s%2FfEhRrZAsY3nQF2N1A%2BCrNAwjF%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDI1MTM5NDkxNjQyMyIMrkUym92xsD%2BN6XrWKqED6xqHHVsqWRvrCKttpypAUvrRfci29ekJWM42SPiIhQKib9ooJo8M952wnIQy0972SYCxn7GEpX9niVkYR3BAVwciNEE%2BMTPz0ws1mSull3FEalwhiIlYg7lfxMsnwxCSAGoLUZ5XYkvmVv4aY1nM1DFY3uFdpS8jXCYH3sbgPaxaC55ZF7P9RZdhm2XB5L9PevMjlg4nQlcWegCzERJvNAe4vcn27tEljy%2FY7gi1XHD8HymblviBJyoCULmgOoA4U%2F9mwgQxOdFWlCUDTqoFwsVRC2Xz4ffR710biKTI2mEHaQIsj8BY6Z1EN7FAM6wkVHqC4y5IcaI6xIkmY3MAuodTqxe1T0Pz%2BXmhSJEcoJWGvlIoyS4uGEsASCpVsbM6Jwgw0XhhxKuiApXa1IidCbWBcNLlMoxclguu91JtkY%2FnH5JxmSr2FGKhP8YhGtef%2BLRpVs3BKm3uk7spzwbpxmKKQYFQnSWFxA5ytnHykpbfju8vasDWPRXHNYLE38Qgx3USRaQBMBOg3ycntiAoDjd%2BTww4Ab7%2FncTgLHG2Nb2CMNDQ%2FMUGOpUCvMWlCKK71xUOjHPDP4ag%2BFq2LSTqf%2BV1eRnnayJYb4befkJMF1IhqSyXv9xAomZqNlHdo8%2F0cwE8bKnQI2s1CdaVVQCPm51tOVJ70m2JE%2Bw0MqRtiPay0xNcIhxvoQMrg0uG%2FL%2BOWVi2Piwt9cMH%2Bk2k%2FxRGEF78Q1aOfLeYunQyNmUsJRGV89u8kF77VPyIQ5pv1RmDr5OADvtxhWvD8vPn3GLu3xRVe1OIQL%2BB4bUvcC7Pl7Dv9mZrdizr1Sj0udM11iAjP9not0QTtMEtXJvlRUnB97nsoS2MJwpUx0C%2FKrE7nb1x57C1EIK5SaEA2cH%2BIzR4rFKjaHGuZqRlluYSrFMgKHcUvpOsWeUrDID9aWMZSw%3D%3D&X-Amz-Signature=366076612bb05ec22aaa73b9e974024760f7786e06309140b4d5185a9df440ed&X-Amz-SignedHeaders=host&response-content-disposition=inline");
                                gestaoAula10.setCurso(cursoGestao);

                                Aula gestaoAula11 = new Aula();
                                gestaoAula11.setTitulo("Aula 4.3:");
                                gestaoAula11.setConteudo("Identificação, análise e plano de resposta a riscos.");
                                gestaoAula11.setVideoUrl("https://masterproject-videos-cursos.s3.us-east-2.amazonaws.com/Aula%204.3%20-%20Treinamento%20-%20Master%20Project%20-%20Gest%C3%A3o%20de%20Projetos%20H%C3%ADbrida-20250405_210645-Meeting%20Recording.mp4?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=ASIATVCCOABDYCJR6OX6%2F20250908%2Fus-east-2%2Fs3%2Faws4_request&X-Amz-Date=20250908T194950Z&X-Amz-Expires=300&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEFwaCXVzLWVhc3QtMiJGMEQCH032ZdpZ%2BT0lFsuC3W49QCQqWnZhN4qzmPlqX3qDDyUCIQCFfpYWkVm9ZVkDEkdk8SyR8s%2FfEhRrZAsY3nQF2N1A%2BCrNAwjF%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDI1MTM5NDkxNjQyMyIMrkUym92xsD%2BN6XrWKqED6xqHHVsqWRvrCKttpypAUvrRfci29ekJWM42SPiIhQKib9ooJo8M952wnIQy0972SYCxn7GEpX9niVkYR3BAVwciNEE%2BMTPz0ws1mSull3FEalwhiIlYg7lfxMsnwxCSAGoLUZ5XYkvmVv4aY1nM1DFY3uFdpS8jXCYH3sbgPaxaC55ZF7P9RZdhm2XB5L9PevMjlg4nQlcWegCzERJvNAe4vcn27tEljy%2FY7gi1XHD8HymblviBJyoCULmgOoA4U%2F9mwgQxOdFWlCUDTqoFwsVRC2Xz4ffR710biKTI2mEHaQIsj8BY6Z1EN7FAM6wkVHqC4y5IcaI6xIkmY3MAuodTqxe1T0Pz%2BXmhSJEcoJWGvlIoyS4uGEsASCpVsbM6Jwgw0XhhxKuiApXa1IidCbWBcNLlMoxclguu91JtkY%2FnH5JxmSr2FGKhP8YhGtef%2BLRpVs3BKm3uk7spzwbpxmKKQYFQnSWFxA5ytnHykpbfju8vasDWPRXHNYLE38Qgx3USRaQBMBOg3ycntiAoDjd%2BTww4Ab7%2FncTgLHG2Nb2CMNDQ%2FMUGOpUCvMWlCKK71xUOjHPDP4ag%2BFq2LSTqf%2BV1eRnnayJYb4befkJMF1IhqSyXv9xAomZqNlHdo8%2F0cwE8bKnQI2s1CdaVVQCPm51tOVJ70m2JE%2Bw0MqRtiPay0xNcIhxvoQMrg0uG%2FL%2BOWVi2Piwt9cMH%2Bk2k%2FxRGEF78Q1aOfLeYunQyNmUsJRGV89u8kF77VPyIQ5pv1RmDr5OADvtxhWvD8vPn3GLu3xRVe1OIQL%2BB4bUvcC7Pl7Dv9mZrdizr1Sj0udM11iAjP9not0QTtMEtXJvlRUnB97nsoS2MJwpUx0C%2FKrE7nb1x57C1EIK5SaEA2cH%2BIzR4rFKjaHGuZqRlluYSrFMgKHcUvpOsWeUrDID9aWMZSw%3D%3D&X-Amz-Signature=9b1a4385ad364879d357c1fd5ef3a173854ce16e3fc40bbde69ba83e0e9f6215&X-Amz-SignedHeaders=host&response-content-disposition=inline");
                                gestaoAula11.setCurso(cursoGestao);

                                // Aulas Módulo 5
                                Aula gestaoAula12 = new Aula();
                                gestaoAula12.setTitulo("Aula 5.1:");
                                gestaoAula12.setConteudo(
                                                "Mapeamento e engajamento das partes interessadas do projeto.");
                                gestaoAula12.setVideoUrl("https://masterproject-videos-cursos.s3.us-east-2.amazonaws.com/Aula%205.1%20-%20Treinamento%20-%20Master%20Project%20-%20Gest%C3%A3o%20de%20Projetos%20H%C3%ADbrida-20250412_150238-Meeting%20Recording.mp4?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=ASIATVCCOABDYCJR6OX6%2F20250908%2Fus-east-2%2Fs3%2Faws4_request&X-Amz-Date=20250908T195021Z&X-Amz-Expires=300&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEFwaCXVzLWVhc3QtMiJGMEQCH032ZdpZ%2BT0lFsuC3W49QCQqWnZhN4qzmPlqX3qDDyUCIQCFfpYWkVm9ZVkDEkdk8SyR8s%2FfEhRrZAsY3nQF2N1A%2BCrNAwjF%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDI1MTM5NDkxNjQyMyIMrkUym92xsD%2BN6XrWKqED6xqHHVsqWRvrCKttpypAUvrRfci29ekJWM42SPiIhQKib9ooJo8M952wnIQy0972SYCxn7GEpX9niVkYR3BAVwciNEE%2BMTPz0ws1mSull3FEalwhiIlYg7lfxMsnwxCSAGoLUZ5XYkvmVv4aY1nM1DFY3uFdpS8jXCYH3sbgPaxaC55ZF7P9RZdhm2XB5L9PevMjlg4nQlcWegCzERJvNAe4vcn27tEljy%2FY7gi1XHD8HymblviBJyoCULmgOoA4U%2F9mwgQxOdFWlCUDTqoFwsVRC2Xz4ffR710biKTI2mEHaQIsj8BY6Z1EN7FAM6wkVHqC4y5IcaI6xIkmY3MAuodTqxe1T0Pz%2BXmhSJEcoJWGvlIoyS4uGEsASCpVsbM6Jwgw0XhhxKuiApXa1IidCbWBcNLlMoxclguu91JtkY%2FnH5JxmSr2FGKhP8YhGtef%2BLRpVs3BKm3uk7spzwbpxmKKQYFQnSWFxA5ytnHykpbfju8vasDWPRXHNYLE38Qgx3USRaQBMBOg3ycntiAoDjd%2BTww4Ab7%2FncTgLHG2Nb2CMNDQ%2FMUGOpUCvMWlCKK71xUOjHPDP4ag%2BFq2LSTqf%2BV1eRnnayJYb4befkJMF1IhqSyXv9xAomZqNlHdo8%2F0cwE8bKnQI2s1CdaVVQCPm51tOVJ70m2JE%2Bw0MqRtiPay0xNcIhxvoQMrg0uG%2FL%2BOWVi2Piwt9cMH%2Bk2k%2FxRGEF78Q1aOfLeYunQyNmUsJRGV89u8kF77VPyIQ5pv1RmDr5OADvtxhWvD8vPn3GLu3xRVe1OIQL%2BB4bUvcC7Pl7Dv9mZrdizr1Sj0udM11iAjP9not0QTtMEtXJvlRUnB97nsoS2MJwpUx0C%2FKrE7nb1x57C1EIK5SaEA2cH%2BIzR4rFKjaHGuZqRlluYSrFMgKHcUvpOsWeUrDID9aWMZSw%3D%3D&X-Amz-Signature=0a49b9ef8c4af525fc7ec6aeed153ea78f4dd3428eef5e4655747c7ad2fd535b&X-Amz-SignedHeaders=host&response-content-disposition=inline");
                                gestaoAula12.setCurso(cursoGestao);

                                Aula gestaoAula13 = new Aula();
                                gestaoAula13.setTitulo("Aula 5.2:");
                                gestaoAula13.setConteudo("Uso de softwares e metodologias de apoio.");
                                gestaoAula13.setVideoUrl("https://masterproject-videos-cursos.s3.us-east-2.amazonaws.com/Aula%205.2%20-%20Treinamento%20-%20Master%20Project%20-%20Gest%C3%A3o%20de%20Projetos%20H%C3%ADbrida-20250412_180826-Meeting%20Recording.mp4?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=ASIATVCCOABDYCJR6OX6%2F20250908%2Fus-east-2%2Fs3%2Faws4_request&X-Amz-Date=20250908T195047Z&X-Amz-Expires=300&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEFwaCXVzLWVhc3QtMiJGMEQCH032ZdpZ%2BT0lFsuC3W49QCQqWnZhN4qzmPlqX3qDDyUCIQCFfpYWkVm9ZVkDEkdk8SyR8s%2FfEhRrZAsY3nQF2N1A%2BCrNAwjF%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDI1MTM5NDkxNjQyMyIMrkUym92xsD%2BN6XrWKqED6xqHHVsqWRvrCKttpypAUvrRfci29ekJWM42SPiIhQKib9ooJo8M952wnIQy0972SYCxn7GEpX9niVkYR3BAVwciNEE%2BMTPz0ws1mSull3FEalwhiIlYg7lfxMsnwxCSAGoLUZ5XYkvmVv4aY1nM1DFY3uFdpS8jXCYH3sbgPaxaC55ZF7P9RZdhm2XB5L9PevMjlg4nQlcWegCzERJvNAe4vcn27tEljy%2FY7gi1XHD8HymblviBJyoCULmgOoA4U%2F9mwgQxOdFWlCUDTqoFwsVRC2Xz4ffR710biKTI2mEHaQIsj8BY6Z1EN7FAM6wkVHqC4y5IcaI6xIkmY3MAuodTqxe1T0Pz%2BXmhSJEcoJWGvlIoyS4uGEsASCpVsbM6Jwgw0XhhxKuiApXa1IidCbWBcNLlMoxclguu91JtkY%2FnH5JxmSr2FGKhP8YhGtef%2BLRpVs3BKm3uk7spzwbpxmKKQYFQnSWFxA5ytnHykpbfju8vasDWPRXHNYLE38Qgx3USRaQBMBOg3ycntiAoDjd%2BTww4Ab7%2FncTgLHG2Nb2CMNDQ%2FMUGOpUCvMWlCKK71xUOjHPDP4ag%2BFq2LSTqf%2BV1eRnnayJYb4befkJMF1IhqSyXv9xAomZqNlHdo8%2F0cwE8bKnQI2s1CdaVVQCPm51tOVJ70m2JE%2Bw0MqRtiPay0xNcIhxvoQMrg0uG%2FL%2BOWVi2Piwt9cMH%2Bk2k%2FxRGEF78Q1aOfLeYunQyNmUsJRGV89u8kF77VPyIQ5pv1RmDr5OADvtxhWvD8vPn3GLu3xRVe1OIQL%2BB4bUvcC7Pl7Dv9mZrdizr1Sj0udM11iAjP9not0QTtMEtXJvlRUnB97nsoS2MJwpUx0C%2FKrE7nb1x57C1EIK5SaEA2cH%2BIzR4rFKjaHGuZqRlluYSrFMgKHcUvpOsWeUrDID9aWMZSw%3D%3D&X-Amz-Signature=bdd84f84b1b5179579c94359957e6e356102efcdd67a4460b2983b92c141fe5b&X-Amz-SignedHeaders=host&response-content-disposition=inline");
                                gestaoAula13.setCurso(cursoGestao);

                                Aula gestaoAula14 = new Aula();
                                gestaoAula14.setTitulo("Aula 5.3:");
                                gestaoAula14.setConteudo("Processos de finalização, lições aprendidas e documentação.");
                                gestaoAula14.setVideoUrl("https://masterproject-videos-cursos.s3.us-east-2.amazonaws.com/Aula%205.3%20-%20Treinamento%20-%20Master%20Project%20-%20Gest%C3%A3o%20de%20Projetos%20H%C3%ADbrida-20250412_211222-Meeting%20Recording.mp4?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=ASIATVCCOABDYCJR6OX6%2F20250908%2Fus-east-2%2Fs3%2Faws4_request&X-Amz-Date=20250908T195109Z&X-Amz-Expires=300&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEFwaCXVzLWVhc3QtMiJGMEQCH032ZdpZ%2BT0lFsuC3W49QCQqWnZhN4qzmPlqX3qDDyUCIQCFfpYWkVm9ZVkDEkdk8SyR8s%2FfEhRrZAsY3nQF2N1A%2BCrNAwjF%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDI1MTM5NDkxNjQyMyIMrkUym92xsD%2BN6XrWKqED6xqHHVsqWRvrCKttpypAUvrRfci29ekJWM42SPiIhQKib9ooJo8M952wnIQy0972SYCxn7GEpX9niVkYR3BAVwciNEE%2BMTPz0ws1mSull3FEalwhiIlYg7lfxMsnwxCSAGoLUZ5XYkvmVv4aY1nM1DFY3uFdpS8jXCYH3sbgPaxaC55ZF7P9RZdhm2XB5L9PevMjlg4nQlcWegCzERJvNAe4vcn27tEljy%2FY7gi1XHD8HymblviBJyoCULmgOoA4U%2F9mwgQxOdFWlCUDTqoFwsVRC2Xz4ffR710biKTI2mEHaQIsj8BY6Z1EN7FAM6wkVHqC4y5IcaI6xIkmY3MAuodTqxe1T0Pz%2BXmhSJEcoJWGvlIoyS4uGEsASCpVsbM6Jwgw0XhhxKuiApXa1IidCbWBcNLlMoxclguu91JtkY%2FnH5JxmSr2FGKhP8YhGtef%2BLRpVs3BKm3uk7spzwbpxmKKQYFQnSWFxA5ytnHykpbfju8vasDWPRXHNYLE38Qgx3USRaQBMBOg3ycntiAoDjd%2BTww4Ab7%2FncTgLHG2Nb2CMNDQ%2FMUGOpUCvMWlCKK71xUOjHPDP4ag%2BFq2LSTqf%2BV1eRnnayJYb4befkJMF1IhqSyXv9xAomZqNlHdo8%2F0cwE8bKnQI2s1CdaVVQCPm51tOVJ70m2JE%2Bw0MqRtiPay0xNcIhxvoQMrg0uG%2FL%2BOWVi2Piwt9cMH%2Bk2k%2FxRGEF78Q1aOfLeYunQyNmUsJRGV89u8kF77VPyIQ5pv1RmDr5OADvtxhWvD8vPn3GLu3xRVe1OIQL%2BB4bUvcC7Pl7Dv9mZrdizr1Sj0udM11iAjP9not0QTtMEtXJvlRUnB97nsoS2MJwpUx0C%2FKrE7nb1x57C1EIK5SaEA2cH%2BIzR4rFKjaHGuZqRlluYSrFMgKHcUvpOsWeUrDID9aWMZSw%3D%3D&X-Amz-Signature=c68e428921cc8c7db117f6f04b9beaf3143153c948b0b5c5a896c938569b7766&X-Amz-SignedHeaders=host&response-content-disposition=inline");
                                gestaoAula14.setCurso(cursoGestao);

                                todasAsAulas
                                                .addAll(List.of(gestaoAula1, gestaoAula2, gestaoAula3, gestaoAula4,
                                                                gestaoAula5, gestaoAula6, gestaoAula7, gestaoAula77, gestaoAula8,
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