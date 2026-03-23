package org.reempreende.presentation.presenter.comerciante;

import org.reempreende.application.service.ServicoService;
import org.reempreende.presentation.interfaces.icomerciante.IComercianteServicoView;
import org.reempreende.presentation.router.AppRouter;

import java.time.LocalDate;
import java.time.LocalTime;

public class ComercianteCriarAgendamentoPresenter {
    private AppRouter appRouter;
    private IComercianteServicoView view;
    private ServicoService service;

    public void createAgendamentoViaServico(LocalTime abre, LocalTime fecha, double duracao) {
        double abreHora = abre.getHour();
        double abreMinuto = abre.getMinute();

        double fechaHora = fecha.getHour();
        double fechaMinuto = fecha.getMinute();

        double aberto = abreHora + (abreMinuto / 60);
        double fechado = fechaHora + (fechaMinuto / 60);

        double cont = aberto;

        while (cont < fechado) {
            // criar agendamento
            cont += duracao / 60;
        }

        if (cont > fechado) {
            // colocar o resto de tempo sobrando como um agendamento
            // top
        }
    }

    public static void main(String[] args) {
        LocalTime abre = LocalTime.of(10, 30);
        LocalTime fecha = LocalTime.of(11, 35);

        double abreNum = abre.getHour();
        double abree = abre.getMinute();

        double fechaNum = fecha.getHour();
        double fechaa = fecha.getMinute();

        System.out.println(abreNum + abree / 60);

        double aberto = abreNum + (abree / 60);
        double fechado = fechaNum + (fechaa / 60);

        double duracao = 30;

        double cont = aberto;

        while (cont < fechado) {
            System.out.println("EEEE");
            cont += duracao / 60;
        }

        if (cont > fechado) {
            System.out.println((Math.floor(cont - fechado) * 100) / 100);
        }
    }
}
