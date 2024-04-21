$ErrorActionPreference = "Stop"

Get-ChildItem -Path "C:\Users\william\Documents\Projets\MODSTS\ServantReloaded\src\main\resources\servantreloadedResources\images\cards" -Recurse -Include @("*.png", "*.jpg", "*.jpeg", "*.bmp", "*.gif") | ForEach-Object {
    $oldName = $_.Name
    $newName = ""
    $words = $oldName -split '_'
    foreach ($word in $words) {
        $newName += $word.Substring(0,1).ToUpper() + $word.Substring(1).ToLower()
    }
    $newName = $newName -replace '(\d+)\.png', '$1.png' # Ajouter "" avant les nombres
    $newName = $newName.Substring(0,1).ToUpper() + $newName.Substring(1) # Mettre la première lettre en majuscule
    
    # Si le fichier se termine par "P.png", le remplace par "_p.png"
    if ($newName -match 'P.png$') {
        $newName = $newName -replace 'P\.png$', '_p.png'
    }
    
    Rename-Item -Path $_.FullName -NewName $newName
}