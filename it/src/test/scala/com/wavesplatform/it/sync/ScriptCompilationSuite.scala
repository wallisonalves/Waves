package com.wavesplatform.it.sync

import com.wavesplatform.it.api.SyncHttpApi._
import com.wavesplatform.it.transactions.BaseTransactionSuite
import scorex.api.http.assets.{SetScriptRequest, SmartIssueRequest}
import scorex.transaction.smart.Script
import com.wavesplatform.state2.EitherExt2

class ScriptCompilationSuite extends BaseTransactionSuite {
  test("Sign broadcast via rest") {
    val sender = notMiner.publicKey.address
    val script = notMiner.scriptCompile("true").script
    val s      = Script.fromBase58String(script).explicitGet()
    println(s)
    notMiner.signAndBroadcast(SmartIssueRequest(2, sender, "name", "desc", 10000, 2, false, Some(script), 100000000, None).toJsObject)
    notMiner.signAndBroadcast(SetScriptRequest(1, sender, None, 100000, None).toJsObject)
  }
}
