package exportation.model.bl;

import exportation.controller.exception.NoExportTracingFoundException;
import lombok.Getter;
import exportation.model.da.ExportTracingDa;
import exportation.model.entity.ExportTracing;
import exportation.model.tools.CRUD;

import java.util.List;

public class ExportTracingBl implements CRUD<ExportTracing> {
    @Getter
    private static ExportTracingBl exportTracingBl = new ExportTracingBl();

    private ExportTracingBl() {
    }

    //save
    @Override
    public ExportTracing save(ExportTracing exportTracing) throws Exception {
        try (ExportTracingDa exportTracingDa = new ExportTracingDa()) {
            exportTracingDa.save(exportTracing);
            return exportTracing;
        }
    }

    //edit
    @Override
    public ExportTracing edit(ExportTracing exportTracing) throws Exception {
        try (ExportTracingDa exportTracingDa = new ExportTracingDa()) {
            if (exportTracingDa.findById(exportTracing.getId()) != null) {
                exportTracingDa.edit(exportTracing);
                return exportTracing;
            } else {
                throw new NoExportTracingFoundException();
            }
        }
    }

    //remove
    @Override
    public ExportTracing remove(int id) throws Exception {
        try (ExportTracingDa exportTracingDa = new ExportTracingDa()) {
            ExportTracing exportTracing = exportTracingDa.findById(id);
            if (exportTracing != null) {
                exportTracingDa.remove(id);
                return exportTracing;
            } else {
                throw new NoExportTracingFoundException();
            }
        }
    }

    //findAll
    @Override
    public List<ExportTracing> findAll() throws Exception {
        try (ExportTracingDa exportTracingDa = new ExportTracingDa()) {
            List<ExportTracing> perosnList = exportTracingDa.findAll();
            if (!perosnList.isEmpty()) {
                return perosnList;
            } else {
                throw new NoExportTracingFoundException();
            }
        }
    }

    //findById
    @Override
    public ExportTracing findById(int id) throws Exception {
        try (ExportTracingDa exportTracingDa = new ExportTracingDa()) {
            ExportTracing exportTracing = exportTracingDa.findById(id);
            if (exportTracing != null) {
                return exportTracing;
            } else {
                throw new NoExportTracingFoundException();
            }
        }
    }
}
