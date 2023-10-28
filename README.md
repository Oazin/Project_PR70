# PR70

## Classes

Main : 
  La classe principale de l'application, responsable de lancer l'application et d'afficher la fenêtre principale.

User : 
Une classe pour représenter les utilisateurs. Elle contiendra des informations telles que le nom d'utilisateur, le mot de passe, et les tâches associées à chaque utilisateur.

Task : 
Une classe pour représenter une tâche. Elle peut inclure des attributs tels que le nom de la tâche, la description, la date d'échéance, la priorité, l'état (terminée ou non), etc.

Category : 
Une classe pour représenter les catégories de tâches. Les tâches peuvent être organisées dans des catégories, et cette classe pourrait contenir des informations sur les catégories, telles que le nom, la couleur, etc.

Admin :
Une classe pour représenter l'administrateur du système. Elle pourrait avoir des privilèges spéciaux pour gérer les catégories, superviser l'activité des utilisateurs et envoyer des rappels.

TaskManager : 
Une classe responsable de la gestion des tâches, y compris la création, la modification et la suppression de tâches.

CategoryManager : 
Une classe responsable de la gestion des catégories de tâches.

UserManager :
Une classe pour gérer les utilisateurs, y compris l'authentification, la création de comptes, etc.

AdminDashboard : 
Une classe pour l'interface graphique du tableau de bord de l'administrateur, où il peut effectuer des actions administratives.

UserDashboard : 
Une classe pour l'interface graphique du tableau de bord de l'utilisateur, où les utilisateurs peuvent créer, modifier, supprimer et organiser leurs tâches.

TaskFilter : 
Une classe qui permet aux utilisateurs de filtrer leurs tâches en fonction de différents critères, tels que la date d'échéance, la priorité, etc.

TaskSorter : 
Une classe qui permet aux utilisateurs de trier leurs tâches en fonction de différents critères, tels que la date d'échéance, la priorité, etc.

ReminderService : 
Une classe qui gère l'envoi de rappels aux utilisateurs.
