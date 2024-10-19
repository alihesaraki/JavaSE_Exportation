package exportation.model.bl;

import exportation.controller.exception.NoTransportationFoundException;
import lombok.Getter;
import exportation.model.da.TransportationDa;
import exportation.model.entity.Transportation;
import exportation.model.tools.CRUD;

import java.util.List;

public class TransportationBl implements CRUD<Transportation> {
    @Getter
    private static TransportationBl transportationBl = new TransportationBl();

    private TransportationBl() {
    }

    //save
    @Override
    public Transportation save(Transportation transportation) throws Exception {
        try (TransportationDa transportationDa = new TransportationDa()) {
            transportationDa.save(transportation);
            return transportation;
        }
    }

    //edit
    @Override
    public Transportation edit(Transportation transportation) throws Exception {
        try (TransportationDa transportationDa = new TransportationDa()) {
            if (transportationDa.findById(transportation.getId()) != null) {
                transportationDa.edit(transportation);
                return transportation;
            } else {
                throw new NoTransportationFoundException();
            }
        }
    }

    //remove
    @Override
    public Transportation remove(int id) throws Exception {
        try (TransportationDa transportationDa = new TransportationDa()) {
            Transportation transportation = transportationDa.findById(id);
            if (transportation != null) {
                transportationDa.remove(id);
                return transportation;
            } else {
                throw new NoTransportationFoundException();
            }
        }
    }

    //findAll
    @Override
    public List<Transportation> findAll() throws Exception {
        try (TransportationDa transportationDa = new TransportationDa()) {
            List<Transportation> perosnList = transportationDa.findAll();
            if (!perosnList.isEmpty()) {
                return perosnList;
            } else {
                throw new NoTransportationFoundException();
            }
        }
    }

    //findById
    @Override
    public Transportation findById(int id) throws Exception {
        try (TransportationDa transportationDa = new TransportationDa()) {
            Transportation transportation = transportationDa.findById(id);
            if (transportation != null) {
                return transportation;
            } else {
                throw new NoTransportationFoundException();
            }
        }
    }
}
