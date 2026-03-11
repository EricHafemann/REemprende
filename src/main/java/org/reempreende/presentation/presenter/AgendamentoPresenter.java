package org.reempreende.presentation.presenter;

import org.reempreende.application.service.AgendamentoService;
import org.reempreende.domain.entities.Agendamento;
import org.reempreende.infrastructure.utility.Cores;
import org.reempreende.presentation.exception.InvalidDateException;
import org.reempreende.presentation.exception.ObjectNullException;
import org.reempreende.presentation.interfaces.iagendamento.IAgendamentoView;

import java.time.LocalDateTime;
import java.util.List;

public class AgendamentoPresenter {
    private IAgendamentoView iAgendamentoView;
    private AgendamentoService agendamentoService;

    public AgendamentoPresenter(IAgendamentoView iAgendamentoView) {
        this.iAgendamentoView = iAgendamentoView;
        this.agendamentoService = new AgendamentoService();
    }

    public void checkInformation(Agendamento agendamento) {
        if (agendamento.getDataInicio() == null) {
            throw new InvalidDateException(Cores.VERMELHO + "Data de início inválida!" + Cores.RESET);
        }

        if (agendamento.getDataFim() == null) {
            throw new InvalidDateException(Cores.VERMELHO + "Data de fim inválida!" + Cores.RESET);
        }

        if (agendamento.getDataInicio().isAfter(agendamento.getDataFim())) {
            throw new InvalidDateException(Cores.VERMELHO + "Data de início esta marcada para iniciar depois da Data de finalizar!" + Cores.RESET);
        }

        LocalDateTime dataHoje = LocalDateTime.now();

        if (agendamento.getDataInicio().isBefore(dataHoje)) {
            throw new InvalidDateException(Cores.VERMELHO + "Data de início é antes do dia de hoje!" + Cores.RESET);
        }

        if (agendamento.getObservacao() == null) {
            throw new ObjectNullException("Observação inválida! (null)");
        }

        if (agendamento.getCliente() == null) {
            throw new ObjectNullException("Cliente inválido! (null)");
        }
    }

    public void insert(Agendamento agendamento) {
        checkInformation(agendamento);

        // inserir
    }

    public Agendamento findById(long id) {
        return null;
    }

    public List<Agendamento> findAll() {
        return null;
    }

    public boolean update() {
        return true;
    }

    public boolean delete(long id) {
        return true;
    }

    public List<Agendamento> findByClienteId(long idClient) {
        return null;
    }

    public List<Agendamento> findByInitialDate(LocalDateTime date) {
        return null;
    }

    public boolean isAvailable(LocalDateTime startTime, LocalDateTime endTime) {
        return true;
    }

    public long countByClientId(long idClient) {
        return 1L;
    }

    public List<Agendamento> findUpcoming() {
        return null;
    }

    public  List<Agendamento> findPast() {
        return null;
    }

}
