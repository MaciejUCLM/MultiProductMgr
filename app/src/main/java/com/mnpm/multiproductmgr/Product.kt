package com.mnpm.multiproductmgr

class Product {

    private var name: String? = null
    private var power: String? = null
    private var maxVelocity: String?
    private var carbonDioxideEmision: String? = null
    private var mass: String? = null
    private var productionYear: String? = null

    constructor(name: String?, power: String?, maxVelocity: String?, carbonDioxideEmision: String?, mass: String?, productionYear: String?) {
        this.name = name
        this.power = power
        this.maxVelocity = maxVelocity
        this.carbonDioxideEmision = carbonDioxideEmision
        this.mass = mass
        this.productionYear = productionYear
    }

}