import dao.TableRestaurantDAO;
import io.javalin.Javalin;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        TableRestaurantDAO tableDAO = new TableRestaurantDAO();
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public");
        }).start(8080);

        app.get("/api/tables", ctx -> {
            ctx.json(tableDAO.getToutesLesTables());
        });

        app.put("/api/tables/{id}/statut", ctx -> {
            String idString = ctx.pathParam("id");
            UUID tableId = UUID.fromString(idString);

            String nouveauStatut = ctx.body();

            System.out.println("Mise à jour demandée pour table : " + tableId + " vers " + nouveauStatut);

            boolean succes = tableDAO.mettreAJourStatut(tableId, nouveauStatut);

            if (succes) {
                ctx.status(200).result("Statut mis à jour avec succès");
            } else {
                ctx.status(500).result("Erreur lors de la sauvegarde en base");
            }
        });

        System.out.println("API REST en ligne !");
    }
}