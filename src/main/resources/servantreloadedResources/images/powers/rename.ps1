$ErrorActionPreference = "Stop"

Get-ChildItem -Path "C:\Users\william\Documents\Projets\MODSTS\ServantReloaded\src\main\resources\servantreloadedResources\images\powers" -Recurse -Include @("*.png", "*.jpg", "*.jpeg", "*.bmp", "*.gif") | ForEach-Object {
    $oldName = $_.Name
    $newName = ""
    $words = $oldName -split '_'
    foreach ($word in $words) {
        $newName += $word.Substring(0,1).ToUpper() + $word.Substring(1).ToLower()
    }
    $newName = $newName -replace '(\d+)\.png', 'Power$1.png' # Ajouter "Power" avant les nombres
    $newName = $newName.Substring(0,1).ToUpper() + $newName.Substring(1) # Mettre la première lettre en majuscule
    Rename-Item -Path $_.FullName -NewName $newName
}
