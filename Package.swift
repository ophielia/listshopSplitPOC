// swift-tools-version:5.3
import PackageDescription

// BEGIN KMMBRIDGE VARIABLES BLOCK (do not edit)
let remoteKotlinUrl = "https://maven.pkg.github.com/ophielia/listshopSplitPOC/com/listshop/bff/allshared-kmmbridge/0.1.1/allshared-kmmbridge-0.1.1.zip"
let remoteKotlinChecksum = "c5e6dab43d839cdd68f5cfbe5d28a88380041ff562142e36857ddf32a690be19"
let packageName = "allshared"
// END KMMBRIDGE BLOCK

let package = Package(
    name: packageName,
    platforms: [
        .iOS(.v13)
    ],
    products: [
        .library(
            name: packageName,
            targets: [packageName]
        ),
    ],
    targets: [
        .binaryTarget(
            name: packageName,
            url: remoteKotlinUrl,
            checksum: remoteKotlinChecksum
        )
        ,
    ]
)