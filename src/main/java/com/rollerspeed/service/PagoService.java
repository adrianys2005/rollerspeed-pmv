package com.rollerspeed.service;

import com.rollerspeed.model.Pago;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PagoService {
    private final List<Pago> pagos = new ArrayList<>();
    private final AtomicLong seq = new AtomicLong(1);

    public PagoService() {
        pagos.add(new Pago(seq.getAndIncrement(), "Mariana Sofia Pacheco", "1082938471", "Matricula Anual + Mes", 180000, "PSE", "Pagado", "2026-09-05", "PSE-98234"));
        pagos.add(new Pago(seq.getAndIncrement(), "Carlos Andres Bermudez", "1083472819", "Mensualidad Nivel Intermedio", 120000, "Transferencia Bancaria", "Pagado", "2026-09-10", "TRF-45812"));
        pagos.add(new Pago(seq.getAndIncrement(), "Valeria Lucia Gomez", "1081928374", "Mensualidad Semillero Infantil", 100000, "Efectivo", "Pendiente", "2026-09-18", "EFE-00921"));
        pagos.add(new Pago(seq.getAndIncrement(), "Juan David Fernandez", "1085738291", "Uniforme y Kit de Proteccion", 150000, "Tarjeta de Credito", "Pagado", "2026-09-12", "TC-33829"));
        pagos.add(new Pago(seq.getAndIncrement(), "Camila Andrea Rojas", "1089283746", "Mensualidad Alto Rendimiento", 140000, "PSE", "Pendiente", "2026-09-19", "PSE-10928"));
    }

    public synchronized List<Pago> obtenerTodos() {
        return Collections.unmodifiableList(new ArrayList<>(pagos));
    }

    public synchronized void registrarPago(Pago pago) {
        if (pago.getId() == null) {
            pago.setId(seq.getAndIncrement());
        }
        pagos.add(0, pago);
    }

    public synchronized double calcularTotalRecaudado() {
        return pagos.stream()
                .filter(p -> "Pagado".equalsIgnoreCase(p.getEstado()))
                .mapToDouble(Pago::getMonto)
                .sum();
    }

    public synchronized java.util.Optional<Pago> obtenerPorId(Long id) {
        return pagos.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public synchronized long contarPendientes() {
        return pagos.stream()
                .filter(p -> "Pendiente".equalsIgnoreCase(p.getEstado()))
                .count();
    }
}
