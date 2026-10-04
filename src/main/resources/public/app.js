let tablesData = [];
let tableSelectionnee = null;

async function chargerTablesDepuisAPI() {
    try {
        const response = await fetch('/api/tables');
        if (!response.ok) throw new Error(`Erreur HTTP: ${response.status}`);
        const tablesSupabase = await response.json();

        tablesData = tablesSupabase.map(table => {
            let nomAile = 'principale';
            if (table.numeroTable >= 10 && table.numeroTable < 20) nomAile = 'terrasse';
            else if (table.numeroTable >= 20 && table.numeroTable < 30) nomAile = 'secondaire-1';
            else if (table.numeroTable >= 30 && table.numeroTable < 40) nomAile = 'secondaire-2';
            else if (table.numeroTable >= 40) nomAile = 'etage-1';

            return {
                id: table.id,
                numero: table.numeroTable,
                capacite: table.capacite,
                aile: nomAile,
                statut: table.statutActuel || 'LIBRE'
            };
        });

        dessinerPlan();
    } catch (error) {
        console.error("Erreur de chargement :", error);
    }
}

function determinerCouleur(table) {
    if (table.statut === 'OCCUPEE') return 'occupee';
    if (table.statut === 'RESERVEE') return 'reservee';
    return 'libre';
}

function dessinerPlan() {
    ['principale', 'terrasse', 'secondaire-1', 'secondaire-2', 'etage-1'].forEach(id => {
        document.getElementById(`grille-${id}`).innerHTML = '';
    });

    tablesData.forEach(table => {
        const classeCouleur = determinerCouleur(table);
        const divTable = document.createElement('div');
        divTable.className = `table ${classeCouleur}`;
        divTable.innerHTML = `T${table.numero}<br><small>${table.capacite} places</small>`;

        divTable.addEventListener('click', () => ouvrirModale(table));

        document.getElementById(`grille-${table.aile}`).appendChild(divTable);
    });
}

function ouvrirModale(table) {
    tableSelectionnee = table;
    document.getElementById('modale-titre').innerText = `Table ${table.numero} (${table.capacite} places)`;
    document.getElementById('modale').style.display = 'flex';
}

function fermerModale() {
    document.getElementById('modale').style.display = 'none';
    tableSelectionnee = null;
}

async function changerStatut(nouveauStatut) {
    if (!tableSelectionnee) return;

    try {
        const response = await fetch(`/api/tables/${tableSelectionnee.id}/statut`, {
            method: 'PUT',
            headers: { 'Content-Type': 'text/plain' },
            body: nouveauStatut
        });

        if (response.ok) {
            tableSelectionnee.statut = nouveauStatut;
            console.log(`Table ${tableSelectionnee.numero} sauvegardée en ${nouveauStatut}`);

            fermerModale();
            dessinerPlan();
        } else {
            alert("Erreur serveur lors de la sauvegarde.");
        }
    } catch (error) {
        console.error("Erreur réseau :", error);
    }
}

chargerTablesDepuisAPI();